package com.example.class10

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val radius = 100f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = radius.toInt() * 2
        var height = radius.toInt() * 2
        width = resolveSize(width, widthMeasureSpec)
        height = resolveSize(height, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }
}