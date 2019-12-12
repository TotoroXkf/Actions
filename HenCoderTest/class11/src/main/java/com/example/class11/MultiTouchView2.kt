package com.example.class11

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * author : xiakaifa
 * 2019/12/12
 */
class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    private var centerX = 0f
    private var centerY = 0f
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        bitmap = getBitmap(resources, R.drawable.image, getDp(300f).toInt())
        centerX = bitmap.width / 2f
        centerY = bitmap.height / 2f
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                var averageX = 0f
                var averageY = 0f
                for (i in 0 until event.pointerCount) {
                    averageX += event.getX(i)
                    averageY += event.getY(i)
                }
                averageX /= event.pointerCount
                averageY /= event.pointerCount
                centerX = averageX
                centerY = averageY
            }
        }
        invalidate()
        return true
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, centerX - bitmap.width / 2, centerY - bitmap.width / 2, paint)
    }
}