package com.xkf.trainingplatform.base

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isSelected"])
fun setSelected(view: TextView, selected: Boolean) {
    view.isSelected = selected
}

@BindingAdapter(value = ["imagePath"])
fun setImage(view: ImageView, id: Int) {
    if (id != 0) {
        view.setImageResource(id)
    }
}