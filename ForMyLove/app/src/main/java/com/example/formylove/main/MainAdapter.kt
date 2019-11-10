package com.example.formylove.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formylove.R

class MainAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_list, parent, false))
    }

    override fun getItemCount(): Int = 100

    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {

    }
}

class MainViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

}