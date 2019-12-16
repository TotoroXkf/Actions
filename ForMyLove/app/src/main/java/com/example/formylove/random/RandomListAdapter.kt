package com.example.formylove.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R

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
            
            }
            TYPE_THING_ITEM -> {
                return OperationalAreaViewHolder(
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
    
    override fun getItemCount(): Int = viewModel.thingsList.size + 1
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    
    }
}

class OperationalAreaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}

class ThingsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}