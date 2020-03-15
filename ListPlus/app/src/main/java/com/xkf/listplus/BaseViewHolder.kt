package com.xkf.listplus

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class BaseViewHolder<Model>(itemView: View) : ViewHolder(itemView) {
    protected fun bindData(model: Model) {}
}