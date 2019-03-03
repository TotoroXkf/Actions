package com.xiakaifa.app.recyclerviewtest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item,viewGroup,false))
    }

    override fun getItemCount(): Int =1000


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = position.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView)!!
    }
}

