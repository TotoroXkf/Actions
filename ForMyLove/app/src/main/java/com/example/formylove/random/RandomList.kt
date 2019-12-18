package com.example.formylove.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
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
        return ThingsItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_random_thing_list,
                parent,
                false
            )
        )
    }
    
    override fun getItemCount(): Int = viewModel.currentThingsList.size
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ThingsItemViewHolder -> {
                holder.bind(viewModel.currentThingsList[position])
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

class DeleteTouchCallback(private val viewModel: RandomViewModel) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.LEFT,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
    
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewModel.deleteThing(viewHolder.adapterPosition)
    }
}