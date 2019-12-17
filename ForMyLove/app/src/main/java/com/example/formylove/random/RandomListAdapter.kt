package com.example.formylove.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import kotlinx.android.synthetic.main.item_random_thing_edit.view.*
import kotlinx.android.synthetic.main.item_random_thing_list.view.*

const val TYPE_THING_ITEM = 0
const val TYPE_OPERATIONAL_AREA = 1

class RandomListAdapter(
    private val viewModel: RandomViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
                holder.bind(viewModel, position)
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
        const val STATE_COMPUTING = 2
        const val STATE_CONFIRMED = 3
    }
    
    private var state = 0
    
    fun bind(viewModel: RandomViewModel, position: Int) {
        if (viewModel.currentThingsList.isEmpty()) {
            itemView.imgShadow.visibility = View.GONE
        }
        
        itemView.btnConfirm.setOnClickListener {
        
        }
        itemView.btnCompute.setOnClickListener {
        
        }
        itemView.btnReset.setOnClickListener {
        
        }
    }
    
    fun setState(newState: Int) {
        state = newState
        when (state) {
            STATE_INIT -> {
                // 第一次进入页面
                
            }
            STATE_INPUT -> {
            
            }
            STATE_CONFIRMED -> {
            
            }
            STATE_COMPUTING -> {
            
            }
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