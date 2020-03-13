package com.xkf.multitype

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * author : xiakaifa
 * 2020/3/11
 */
class MultiTypeAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    val typePool = TypePool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    private fun clear() {
        typePool.clear()
    }

    protected class ItemDataObserver(
        val startPosition: Int,
        val count: Int,
        val index: Int
    ) : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {

        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {

        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {

        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {

        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {

        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {

        }
    }
}