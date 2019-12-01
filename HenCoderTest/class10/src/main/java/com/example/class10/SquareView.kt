package com.example.class10

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class SquareView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        
        val measureWidth = measuredWidth
        val measureHeight = measuredHeight
        val size = measureWidth.coerceAtMost(measureHeight)
        val finalWidth = View.resolveSize(size, widthMeasureSpec)
        val finalHeight = View.resolveSize(size, heightMeasureSpec)
        setMeasuredDimension(finalWidth, finalHeight)
    }
}