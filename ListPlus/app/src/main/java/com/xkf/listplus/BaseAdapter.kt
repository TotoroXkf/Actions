package com.xkf.listplus

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.atomic.AtomicInteger

/**
 * author : xiakaifa
 * 2020/3/15
 */
class BaseAdapter : RecyclerView.Adapter<BaseViewHolder<ItemModel>>() {
    val typeValue = AtomicInteger(0)

    val dataList = mutableListOf<ItemModel>()
    val viewholderMap = hashMapOf<Class<*>, ViewHolderCreator>()
    val typeMap = hashMapOf<Int, Class<*>>()

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemModel> {
        val clazz = typeMap[viewType]
        clazz?.let {
            val viewHolderCreator = viewholderMap[clazz]
            return viewHolderCreator!!.getViewHolder(parent)
        }

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder<ItemModel>, position: Int) {
        holder.bindData(dataList[position])
    }
}