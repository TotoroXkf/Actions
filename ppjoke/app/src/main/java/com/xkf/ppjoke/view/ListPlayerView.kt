package com.xkf.ppjoke.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.xkf.ppjoke.databinding.LayoutPlayerViewBinding

class ListPlayerView : FrameLayout {
    private var category = ""
    private var videoUrl = ""
    private var layoutPlayerViewBinding: LayoutPlayerViewBinding =
        LayoutPlayerViewBinding.inflate(LayoutInflater.from(context), this, true)
    
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    
    fun bindData(
        category: String,
        widthPx: Int,
        heightPx: Int,
        coverUrl: String,
        videoUrl: String
    ) {
        this.category = category
        this.videoUrl = videoUrl
        
    }
}