package com.xkf.multitype

import androidx.recyclerview.widget.RecyclerView

abstract class ItemAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    var type = -1
}