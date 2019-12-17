package com.example.formylove.random

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import com.example.formylove.utils.showSnakeBar
import kotlinx.android.synthetic.main.item_random_thing_edit.view.*
import kotlinx.android.synthetic.main.item_random_thing_list.view.*

const val TYPE_THING_ITEM = 0
const val TYPE_OPERATIONAL_AREA = 1

class RandomListAdapter(
    private val viewModel: RandomViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    init {
        viewModel.addLiveData.observe(lifecycleOwner, Observer {
            notifyItemInserted(itemCount - 1)
        })
        
        viewModel.deleteLiveData.observe(lifecycleOwner, Observer {
            notifyItemRemoved(it)
        })
        
        viewModel.resetLiveData.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
    }
    
    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            TYPE_OPERATIONAL_AREA
        } else {
            TYPE_THING_ITEM
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_OPERATIONAL_AREA -> {
                return OperationalAreaViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_random_thing_edit,
                        parent,
                        false
                    )
                )
            }
            TYPE_THING_ITEM -> {
                return ThingsItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_random_thing_list,
                        parent,
                        false
                    )
                )
            }
        }
        
        return OperationalAreaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_random_thing_list,
                parent,
                false
            )
        )
    }
    
    override fun getItemCount(): Int = viewModel.currentThingsList.size + 1
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OperationalAreaViewHolder -> {
                holder.bind(viewModel)
            }
            is ThingsItemViewHolder -> {
                holder.bind(viewModel.currentThingsList[position])
            }
        }
    }
}

class OperationalAreaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
    
    fun bind(viewModel: RandomViewModel) {
        if (viewModel.isFirstLoadList) {
            setState(STATE_INIT)
        }
        itemView.textInputLayout.editText!!.addTextChangedListener(object : TextWatcher {
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
        itemView.btnConfirm.setOnClickListener {
            if (getInputText().isEmpty()) {
                showSnakeBar(it, "当前没有输入~~~", false)
                return@setOnClickListener
            }
            viewModel.isFirstLoadList = false
            viewModel.addNewThing(getInputText())
            setState(STATE_CONFIRMED)
        }
        
        // 计算按钮
        itemView.btnCompute.setOnClickListener {
            if (getInputText().isNotEmpty()) {
                showSnakeBar(it, "当前还存在着输入~~~", false)
                return@setOnClickListener
            }
            viewModel.computeRandom {
                setState(STATE_AFTER_COMPUTE)
            }
            setState(STATE_COMPUTING)
        }
        
        // 重置按钮
        itemView.btnReset.setOnClickListener {
            if (getInputText().isNotEmpty()) {
                showSnakeBar(it, "当前还存在着输入~~~", false)
                return@setOnClickListener
            }
            viewModel.resetThingsList()
            setState(STATE_RESET)
        }
    }
    
    private fun getInputText(): String {
        return itemView.textInputLayout.editText!!.text.toString()
    }
    
    private fun setState(newState: Int) {
        state = newState
        val imgShadow = itemView.imgShadow
        val editText = itemView.textInputLayout.editText!!
        val btnConfirm = itemView.btnConfirm
        val btnCompute = itemView.btnCompute
        val btnReset = itemView.btnReset
        
        when (state) {
            STATE_INIT -> {
                // 第一次进入页面
                imgShadow.visibility = View.GONE
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
                imgShadow.visibility = View.VISIBLE
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

class ThingsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(thing: String) {
        if (thing.isNotEmpty()) {
            itemView.textContent.text = thing
        }
    }
}