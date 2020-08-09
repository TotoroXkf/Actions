package com.xkf.trainingplatform.base

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isSelected"])
fun setSelected(view: TextView, selected: Boolean) {
    view.isSelected = selected
}