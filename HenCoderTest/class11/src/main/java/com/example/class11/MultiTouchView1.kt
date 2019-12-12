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
 * 2019/12/10
 */
class MultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private lateinit var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0f
    private var centerY = 0f
    private var trackId = 0
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        bitmap = getBitmap(resources, R.drawable.image, getDp(300f).toInt())
        centerX = (bitmap.width / 2).toFloat()
        centerY = (bitmap.height / 2).toFloat()
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                centerX = event.x
                centerY = event.y
                trackId = event.getPointerId(0)
            }
            MotionEvent.ACTION_MOVE -> {
                centerX = event.getX(event.findPointerIndex(trackId))
                centerY = event.getY(event.findPointerIndex(trackId))
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
            
            }
            MotionEvent.ACTION_POINTER_UP -> {
                if (event.pointerCount == 1) {
                    trackId = 0
                    return true
                }
                var index = event.findPointerIndex(trackId)
                if (index == event.actionIndex) {
                    index = if (index == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackId = event.getPointerId(index)
                }
            }
        }
        invalidate()
        return true
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, centerX - bitmap.width / 2, centerY - bitmap.height / 2, paint)
    }
}