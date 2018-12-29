package com.example.myapp.randomthings

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.myapp.R

class RandomThingsFragment : Fragment() {
    //事件list
    private val things = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var thingsList: ListView
    private lateinit var addButton: FloatingActionButton
    private lateinit var computeButton: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = ArrayAdapter(container?.context!!, android.R.layout.simple_list_item_1, things)
        val view = inflater.inflate(R.layout.fragment_things, container, false)

        thingsList = view.findViewById(R.id.thingsList)
        addButton = view.findViewById(R.id.addButton)
        computeButton = view.findViewById(R.id.computeButton)

        //处理事件list item的点击
        thingsList.adapter = adapter
        thingsList.setOnItemClickListener { _, _, index, _ ->
            //确认等于修改事件值
            val handleAction = { thing: String ->
                things[index] = thing
                adapter.notifyDataSetChanged()
            }

            //取消等于删除这个事件
            val cancelAction = { _: String ->
                things.removeAt(index)
                adapter.notifyDataSetChanged()
            }

            val alertDialog = createInputDialog(handleAction = handleAction, title = "设置事件",
                    negativeButtonName = "删除", text = things[index], cancelAction = cancelAction)
            alertDialog.show()
        }

        //处理添加按钮的事件
        addButton.setOnClickListener {
            //点击确定
            //如果已经存在了就不添加。不存在就按照@字符分词
            val handleAction = { thing: String ->
                if (!things.contains(thing)) {
                    val thingsArray = thing.split("@")
                    for (item in thingsArray) {
                        things.add(item)
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "已经输入过了，换一个吧", Toast.LENGTH_SHORT).show()
                }
            }
            val alertDialog = createInputDialog(handleAction = handleAction, title = "输入事件")
            alertDialog.show()
        }

        //计算按钮的处理
        computeButton.setOnClickListener {
            if (things.isNotEmpty()) {
                val thing = getResult()
                val builder = AlertDialog.Builder(activity!!, R.style.dialog_soft_input)
                builder.apply {
                    setTitle("最后结果")
                    setMessage("最后的结果是：$thing \n就这个结果吧，不要改了哦")
                }
                builder.create().show()
            } else {
                Toast.makeText(activity!!, "貌似什么都没有", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    /*
     * 计算随机事件的结果
     * 创建一个map，key为事件list的下标，value为统计次数
     * 一共摇事件数*1000次，摇0-1的随机值，乘长度转换为int得到下标，对应加1
     * 最后统计次数最多的就是结果
     */
    private fun getResult(): String {
        val thingsMap = HashMap<Int, Int>()
        for (i in things.indices) {
            thingsMap[i] = 0
        }
        for (i in 1..things.size * 1000) {
            val x = (Math.random() * (things.size)).toInt()
            thingsMap[x] = thingsMap[x]!! + 1
        }
        val result = thingsMap.maxBy { it.value }
        return things[result!!.key]
    }

    /*
     * 创建一个具有输入框的dialog
     * 参数说明如下
     * handleAction：点击确认的处理
     * title：弹窗的标题
     * positiveButtonName：确认按钮的名字
     * negativeButtonName：取消按钮的名字
     * text：显示在输入框里面的内容
     * cancelAction： 取消按钮处理
     */
    private fun createInputDialog(handleAction: (String) -> Unit, title: String, positiveButtonName: String = "确认",
                                  negativeButtonName: String = "取消", text: String = "",
                                  cancelAction: (String) -> Unit = {}): AlertDialog {
        //创建Layout和EditText，然后添加属性，加入FrameLayout
        val frameLayout = FrameLayout(context!!)
        val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val margin = 60
        layoutParams.leftMargin = margin
        layoutParams.rightMargin = margin
        val editText = EditText(context)
        if (text == "") {
            editText.hint = "纳尼纳尼？"
        } else {
            editText.setText(text.toCharArray(), 0, text.length)
        }
        frameLayout.addView(editText, layoutParams)

        //创建弹窗，注意这个style的参数，它确保了键盘弹出时会上移
        val builder = AlertDialog.Builder(activity!!, R.style.dialog_soft_input)
        builder.apply {
            setCancelable(true)
            setTitle(title)
            setView(frameLayout)
            setPositiveButton(positiveButtonName) { _, _ ->
                val thing = editText.text.toString()
                handleAction(thing)
            }
            setNegativeButton(negativeButtonName) { _, _ ->
                val thing = editText.text.toString()
                cancelAction(thing)
            }
        }
        val alertDialog = builder.create()
        //使得弹窗出现时键盘能自动弹出
        alertDialog.setOnShowListener {
            val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
        return alertDialog
    }

}