package com.xkf.ppjoke.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class PPImageView : androidx.appcompat.widget.AppCompatImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    
    companion object {
        @SuppressLint("CheckResult")
        @BindingAdapter(value = ["imageUrl", "isCircle"])
        fun setImageData(view: PPImageView, imageUrl: String, isCircle: Boolean) {
            val builder = Glide.with(view).load(imageUrl)
            if (isCircle) {
                builder.transform(CircleCrop())
            }
            val layoutParams = view.layoutParams
            if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
                builder.override(layoutParams.width, layoutParams.height)
            }
            builder.into(view)
        }
    }
}