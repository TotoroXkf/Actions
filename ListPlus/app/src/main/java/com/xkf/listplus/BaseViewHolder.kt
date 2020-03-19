package com.xkf.listplus

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

open class BaseViewHolder<Model : ItemModel>(itemView: View) : ViewHolder(itemView) {
    init {
        initViews()
    }

    protected fun initViews() {}

    public open fun bindData(model: Model) {}
}

abstract class ViewHolderCreator {
    abstract fun getViewHolder(parent: ViewGroup): BaseViewHolder<ItemModel>
}

class MyViewHolder(itemView: View) : BaseViewHolder<MyModel>(itemView) {
    override fun bindData(model: MyModel) {
        super.bindData(model)
    }
}

class MyModel : ItemModel {}