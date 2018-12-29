package com.example.myapp.datecompute

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapp.R
import com.example.myapp.util.dip2px
import com.example.myapp.util.getWindowWidth
import org.jetbrains.anko.support.v4.toast

class DateComputeFragment : Fragment() {
    private val chipsName = arrayListOf(
            "显示日历看看",
            "从今天向后X天是？？？",
            "从某一天向后X天是？？？",
            "从今天到某一天中间隔了多少天？？？",
            "从某一天到某一天中间隔了多少天？？？"
    )

    private lateinit var newView: View
    private lateinit var backButton: FloatingActionButton
    private lateinit var actionList: ListView
    private lateinit var container: ViewGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_date_compute, container, false)
        this.container = container!!

        actionList = view.findViewById(R.id.actionList)
        val adapter = ArrayAdapter<String>(container.context, android.R.layout.simple_list_item_1, chipsName)
        actionList.adapter = adapter
        actionList.setOnItemClickListener { _, _, position, _ ->
            dispatch(position)
        }

        backButton = view.findViewById(R.id.backFloatActionButton)
        backButton.setOnClickListener {
            showList()
            hideBackButton()
            removeView()
        }
        return view
    }

    /*
     * 总调度方法
     */
    private val dispatch = { position: Int ->
        when (position) {
            chipsName.indexOf("显示日历看看") -> {
                hideList()
                showBackButton()
                //todo 在日历下面加一行字显示相差的天数
                val calendarView = CalendarView(context!!)
                addView(calendarView)

                newView.alpha = 0f
                newView.animate().alpha(1f).start()
            }
            chipsName.indexOf("从今天向后X天是？？？"),
            chipsName.indexOf("从今天到某一天中间隔了多少天？？？") -> {
                hideList()
                showBackButton()
                addView(R.layout.date_one_input_and_compute)

                newView.alpha = 0f
                newView.animate().alpha(1f).start()

                when (position) {
                    chipsName.indexOf("从今天向后X天是？？？") -> {
                        handleOneInput("多少天之后？", ::computeAfterSomeDays) { inputText, result ->
                            "从今天向后 $inputText 天，是\n$result"
                        }
                    }
                    chipsName.indexOf("从今天到某一天中间隔了多少天？？？") -> {
                        handleOneInput("哪一天？(输入格式xxxx-xx-xx)", ::computeDaysFromNow) { inputText, result ->
                            "从今天到 $inputText 中间隔了\n$result 天"
                        }
                    }
                }
            }
            chipsName.indexOf("从某一天向后X天是？？？"),
            chipsName.indexOf("从某一天到某一天中间隔了多少天？？？") -> {
                hideList()
                showBackButton()
                addView(R.layout.date_two_input_and_compute)

                newView.alpha = 0f
                newView.animate().alpha(1f).start()

                when (position) {
                    chipsName.indexOf("从某一天向后X天是？？？") -> {
                        handleTwoInput("起始日期(输入格式xxxx-xx-xx)", "向后多少天？",
                                ::computeAfterSomeDaysFromNow) { inputText1, inputText2, result ->
                            "从 $inputText1 往后 $inputText2 天是\n$result"
                        }
                    }
                    chipsName.indexOf("从某一天到某一天中间隔了多少天？？？") -> {
                        handleTwoInput("起始日期(输入格式xxxx-xx-xx)", "目标日期(输入格式xxxx-xx-xx)",
                                ::computeSomeDaysFromSomeDay) { inputText1, inputText2, result ->
                            "从 $inputText1 到 $inputText2 之间相隔\n$result 天"
                        }
                    }
                }
            }
        }
    }

    /*
     * 处理只有一行输入和一个确定按钮的的新View
     * 这个情况下是一种固定的处理情况，只有几个地方不一样
     * 首先是显示的hint，当作参数传入
     * 然后是对输入内容的处理不同，但是都是接受一个字符串然后处理，返回一个字符串。
     * 所以需要一个是计算的方法作为参数传递进来
     * 最后显示在弹窗的内容不同，所以传入一个函数，接受计算的结果和原输入，返回一个内容作为弹窗的显示
     */
    private fun handleOneInput(hint: String, computeMethod: (String) -> String, createMessage: (String, String) -> String) {
        val input = newView.findViewById<TextInputLayout>(R.id.inputDate)
        input.hint = hint
        val computeButton = newView.findViewById<Button>(R.id.computeButton)
        computeButton.setOnClickListener {
            val inputText = input.editText?.text?.toString()!!
            val result = computeMethod(inputText)
            if (result == ERROR) {
                toast("输入无效")
            } else {
                val message = createMessage(inputText, result)
                showDialog(message)
            }
        }
    }

    /*
     * 处理两一行输入和一个确定按钮的的新View
     * 这个情况下是一种固定的处理情况，只有几个地方不一样
     * 首先是显示的hint，有两个不一样，当作参数传入
     * 然后是对输入内容的处理不同，但是都是接受一个字符串然后处理，返回一个字符串。
     * 所以需要一个是计算的方法作为参数传递进来
     * 最后显示在弹窗的内容不同，所以传入一个函数，接受计算的结果和原输入，返回一个内容作为弹窗的显示
     */
    private fun handleTwoInput(hint1: String, hint2: String, computeMethod: (String, String) -> String,
                               createMessage: (String, String, String) -> String) {
        val input1 = newView.findViewById<TextInputLayout>(R.id.inputDate1)
        val input2 = newView.findViewById<TextInputLayout>(R.id.inputDate2)
        input1.hint = hint1
        input2.hint = hint2
        val computeButton = newView.findViewById<Button>(R.id.computeButton)
        computeButton.setOnClickListener {
            val inputText1 = input1.editText?.text?.toString()!!
            val inputText2 = input2.editText?.text?.toString()!!
            val result = computeMethod(inputText1, inputText2)
            if (result == ERROR) {
                toast("输入无效")
            } else {
                val message = createMessage(inputText1, inputText2, result)
                showDialog(message)
            }
        }
    }

    /*
     * 让所有的Chip向左滑动消失
     */
    private fun hideList() {
        if (actionList.x < 0) {
            return
        }
        actionList.animate().translationXBy(-getWindowWidth(context!!).toFloat()).start()
    }

    /*
     * 让所有chip从左边滑回来
     */
    private fun showList() {
        if (actionList.x > 0) {
            return
        }
        actionList.animate().translationXBy(getWindowWidth(context!!).toFloat()).start()
    }

    /*
     * 从底部向上滑出悬浮按钮（要求调用时悬浮按钮在布局里面的右下角底部，并且不可见）
     * 注意一个细节，要添加一个空的listener来打达到消除listener的效果。
     * 因为上一次滑下去动画添加了listener，如果不顶替会导致结束的时候触发回调，让悬浮按消失
     */
    @SuppressLint("RestrictedApi")
    private fun showBackButton() {
        if (backButton.visibility == View.VISIBLE) {
            return
        }
        backButton.visibility = View.VISIBLE
        val distance = getBackButtonMoveDistance()
        backButton.y = backButton.y + distance
        backButton.animate()
                .translationYBy(-distance)
                .setListener(object : AnimatorListenerAdapter() {})
                .start()
    }

    /*
     * 向下滑出悬浮按钮。在动画结束时将悬浮按钮的位置放回到布局里面的右下角，并且为不可见。这样在下次滑出的时候直接调用滑出的方法即可
     */
    @SuppressLint("RestrictedApi")
    private fun hideBackButton() {
        if (backButton.visibility == View.INVISIBLE) {
            return
        }
        val yPosition = backButton.y
        val distance = getBackButtonMoveDistance()
        backButton.animate()
                .translationYBy(distance)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        backButton.y = yPosition
                        backButton.visibility = View.INVISIBLE
                    }
                })
                .start()
    }

    /*
     * 计算悬浮按钮的滑动距离
     * 移动的距离应该等于按钮的高度加上距离底部16dp的margin值（转化为px）
     */
    private fun getBackButtonMoveDistance(): Float {
        val height = backButton.bottom - backButton.top
        val margin = dip2px(context!!, 16f)
        val distance = height + margin + 5
        return distance.toFloat()
    }

    /*
     * 加载添加新的内容View
     */
    private fun addView(layoutId: Int) {
        val constraintLayout = view as ConstraintLayout
        newView = LayoutInflater.from(context).inflate(layoutId, container, false)
        constraintLayout.addView(newView)
    }

    /*
    * 加载添加新的内容View
    */
    private fun addView(view: View) {
        val constraintLayout = getView() as ConstraintLayout
        newView = view
        constraintLayout.addView(view)
    }

    /*
     * 逐渐淡化消失新添加的内容
     * 动画结束，从当前的布局移除新添加进来的内容
     */
    private fun removeView() {
        newView.alpha = 1f
        newView.animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                val constraintLayout = view as ConstraintLayout
                constraintLayout.removeView(newView)
            }
        }).start()
    }

    /*
     * 创建一个显示内容的dialog
     */
    private fun showDialog(message: String) {
        val builder = AlertDialog.Builder(context!!)
        builder.apply {
            setTitle("结果")
            setMessage(message)
        }
        builder.create().show()
    }
}
