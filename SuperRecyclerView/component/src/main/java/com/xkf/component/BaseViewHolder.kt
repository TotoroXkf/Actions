package com.xkf.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun <T> bindData(data: T) {}
}