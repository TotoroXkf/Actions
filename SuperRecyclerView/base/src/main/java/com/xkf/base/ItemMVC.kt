package com.xkf.base

import android.view.View
import android.view.ViewGroup

interface ItemViewCreator<T : View> {
    fun getView(parent: ViewGroup): T
}

interface ItemModel

abstract class ItemController<Model : ItemModel, View : android.view.View> {
    var itemModel: Model? = null
    var itemView: View? = null

    protected fun bind() {
        if (itemModel == null || itemView == null) {
            return
        }
        bind(itemModel!!, itemView!!)
    }

    abstract fun bind(itemModel: Model, itemView: View)
}