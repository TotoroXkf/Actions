package com.example.formylove.random

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.formylove.utils.SnakeBarHelper
import com.example.formylove.utils.getViewModel
import kotlinx.android.synthetic.main.item_random_thing_edit.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OperationalAreaView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    companion object {
        const val STATE_INIT = 0
        const val STATE_INPUT = 1
        const val STATE_INPUT_EMPTY = 2
        const val STATE_COMPUTING = 3
        const val STATE_AFTER_COMPUTE = 4
        const val STATE_CONFIRMED = 5
        const val STATE_RESET = 6
    }
    
    private var state = 0
    
    override fun onFinishInflate() {
        super.onFinishInflate()
        
        val viewModel = getViewModel(RandomViewModel::class.java)
        viewModel ?: return
        
        if (viewModel.isFirstLoadList) {
            setState(STATE_INIT)
        }
        textInputLayout.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            
            }
            
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            
            }
            
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = s.toString()
                if (TextUtils.isEmpty(str)) {
                    if (viewModel.isFirstLoadList) {
                        setState(STATE_INIT)
                    } else {
                        setState(STATE_INPUT_EMPTY)
                    }
                } else {
                    setState(STATE_INPUT)
                }
            }
        })
        
        // 确认按钮
        btnConfirm.setOnClickListener {
            if (getInputText().isEmpty()) {
                SnakeBarHelper.showSnakeBar(it, "当前没有输入~~~", false)
                return@setOnClickListener
            }
            viewModel.isFirstLoadList = false
            viewModel.addNewThing(getInputText())
            setState(STATE_CONFIRMED)
        }
        
        // 计算按钮
        btnCompute.setOnClickListener {
            if (getInputText().isNotEmpty()) {
                SnakeBarHelper.showSnakeBar(it, "当前还存在着输入~~~", false)
                return@setOnClickListener
            }
            setState(STATE_COMPUTING)
            
            // 执行计算
            GlobalScope.launch(Dispatchers.Main) {
                viewModel.computeRandom()
                setState(STATE_AFTER_COMPUTE)
            }
        }
        
        // 重置按钮
        btnReset.setOnClickListener {
            if (getInputText().isNotEmpty()) {
                SnakeBarHelper.showSnakeBar(it, "当前还存在着输入~~~", false)
                return@setOnClickListener
            }
            viewModel.resetThingsList()
            setState(STATE_RESET)
        }
    }
    
    private fun getInputText(): String {
        return textInputLayout.editText!!.text.toString()
    }
    
    private fun setState(newState: Int) {
        state = newState
        val editText = textInputLayout.editText!!
        
        when (state) {
            STATE_INIT -> {
                // 第一次进入页面
                editText.setEnable(true)
                btnConfirm.setEnable(false)
                btnCompute.setEnable(false)
                btnReset.setEnable(false)
            }
            STATE_INPUT -> {
                // 输入中
                editText.setEnable(true)
                btnConfirm.setEnable(true)
                btnCompute.setEnable(false)
                btnReset.setEnable(false)
            }
            STATE_INPUT_EMPTY -> {
                // 输入框为空
                editText.setEnable(true)
                btnConfirm.setEnable(false)
                btnCompute.setEnable(true)
                btnReset.setEnable(true)
            }
            STATE_CONFIRMED -> {
                // 点击确认之后
                editText.setEnable(true)
                editText.setText("")
                btnConfirm.setEnable(false)
                btnCompute.setEnable(true)
                btnReset.setEnable(true)
            }
            STATE_COMPUTING -> {
                // 计算中
                editText.setEnable(false)
                btnConfirm.setEnable(false)
                btnCompute.setEnable(false)
                btnReset.setEnable(false)
            }
            STATE_AFTER_COMPUTE -> {
                // 计算完成之后
                editText.setEnable(true)
                btnConfirm.setEnable(false)
                btnCompute.setEnable(false)
                btnReset.setEnable(true)
            }
            STATE_RESET -> {
                // 点击重置
                editText.setEnable(true)
                btnConfirm.setEnable(false)
                btnCompute.setEnable(true)
                btnReset.setEnable(false)
            }
        }
    }
    
    private fun View.setEnable(enable: Boolean) {
        this.isEnabled = enable
        alpha = if (isEnabled) {
            1f
        } else {
            0.3f
        }
    }
}