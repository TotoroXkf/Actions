package com.xkf.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    companion object {
        const val NON_TYPE = -1
    }

    private val modelList = mutableListOf<ItemModel>()
    private val itemTypeMap = hashMapOf<Class<*>, Int>()
    var typeNum = 0

    override fun getItemViewType(position: Int): Int {
        val typeClass = modelList[position]::class.java
        if (!itemTypeMap.containsKey(typeClass)) {
            return NON_TYPE
        }
        return itemTypeMap[typeClass] ?: NON_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(View(parent.context))
    }

    override fun getItemCount(): Int = modelList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemView = holder.itemView
        val itemModel = modelList[position]

    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    fun addRelation() {

    }

    fun addItemModel(itemModel: ItemModel) {
        //        modelList.add(itemModel)
    }

    fun removeItemModel(itemModel: ItemModel) {

    }
}