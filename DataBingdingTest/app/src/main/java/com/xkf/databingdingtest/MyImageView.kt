package com.xkf.databingdingtest

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter(value = ["imageUrl"])
fun loadNetWorkImage(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}