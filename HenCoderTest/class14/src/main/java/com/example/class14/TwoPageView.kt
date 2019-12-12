package com.example.class14

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import kotlin.math.abs

class TwoPageView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var downX = 0f
    private var downY = 0f
    
    private var currentPage = 0
    
    private val viewConfiguration: ViewConfiguration = ViewConfiguration.get(context!!)
    private val slop = viewConfiguration.scaledTouchSlop
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val childView1 = getChildAt(0)
        val childView2 = getChildAt(1)
        childView1.layout(0, 0, width, height)
        childView2.layout(width, 0, width + width, height)
    }
    
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }
            
            MotionEvent.ACTION_MOVE -> {
                if (abs(event.x - downX) > slop) {
                    return true
                }
            }
        }
        return false
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }
            
            // 滑动分为向左滑动和向右滑动
            MotionEvent.ACTION_MOVE -> {
                val moveX = event.x
                val distance = downX - moveX
                
                if (distance > 0) {
                    // 向左滑动
                    // 已经在最右边，不处理
                    if (currentPage == 1) {
                        return true
                    }
                    // 向左逐步滑动
                    scrollTo(distance.toInt(), 0)
                }
            }
            
            MotionEvent.ACTION_UP -> {
                downX = 0f
            }
        }
        return true
    }
}