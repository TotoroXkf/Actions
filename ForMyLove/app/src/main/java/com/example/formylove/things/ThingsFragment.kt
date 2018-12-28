package com.example.formylove.things

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.formylove.R
import kotlinx.android.synthetic.main.fragment_things.*
import android.widget.*


class ThingsFragment : Fragment() {
    private val things = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapter = ArrayAdapter(container?.context!!, android.R.layout.simple_list_item_1, things)
        return inflater.inflate(R.layout.fragment_things, container, false)
    }

    override fun onResume() {
        super.onResume()

        //listView的处理
        thingsList.adapter = adapter
        thingsList.setOnItemClickListener { _, _, index, _ ->
            val handleAction = { thing: String ->
                things[index] = thing
                adapter.notifyDataSetChanged()
            }

            val cancelAction = { _: String ->
                things.removeAt(index)
                adapter.notifyDataSetChanged()
            }

            val alertDialog = createInputDialog(handleAction = handleAction, title = "设置事件",
                    negativeButtonName = "删除", text = things[index], cancelAction = cancelAction)
            alertDialog.show()
        }

        //添加按钮的处理
        addButton.setOnClickListener {
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
    }

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
        for ((key, value) in thingsMap) {
            Log.e("xkf123456789", "$key---$value")
        }
        return things[result!!.key]
    }

    private fun createInputDialog(handleAction: (String) -> Unit, title: String, positiveButtonName: String = "确认",
                                  negativeButtonName: String = "取消", text: String = "",
                                  cancelAction: (String) -> Unit = {}): AlertDialog {
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
        alertDialog.setOnShowListener {
            val inputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
        return alertDialog
    }

}
