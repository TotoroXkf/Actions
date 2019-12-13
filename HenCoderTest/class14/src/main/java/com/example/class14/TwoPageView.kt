package com.example.class14

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import android.widget.Toast
import kotlin.math.abs

class TwoPageView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var downX = 0f
    private var downY = 0f
    
    private var currentPage = 0
    
    private val viewConfiguration: ViewConfiguration = ViewConfiguration.get(context!!)
    private val slop = viewConfiguration.scaledTouchSlop
    
    private val scroller = OverScroller(context)
    
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
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        getChildAt(0).setOnClickListener {
            Toast.makeText(context, "我是第0个子View", Toast.LENGTH_SHORT).show()
        }
        getChildAt(1).setOnClickListener {
            Toast.makeText(context, "我是第1个子View", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> onDown(event)
            
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
            MotionEvent.ACTION_DOWN -> onDown(event)
            
            MotionEvent.ACTION_MOVE -> onDragMove(event)
            
            MotionEvent.ACTION_UP -> onUp(event)
        }
        return true
    }
    
    private fun onDown(event: MotionEvent) {
        downX = event.x
        downY = event.y
    }
    
    private fun onDragMove(event: MotionEvent) {
        if (currentPage == 0) {
            val dragValue = (downX - event.x).toInt()
            if (dragValue > 0) {
                scrollTo(dragValue, 0)
            }
        } else {
            val dragValue = (event.x - downX).toInt()
            if (dragValue > 0) {
                scrollTo(width - dragValue, 0)
            }
        }
    }
    
    private fun onUp(event: MotionEvent) {
        val scrollDistance = getScrollDistance()
        if (scrollDistance < width / 2) {
            currentPage = 0
            scroller.startScroll(scrollX, 0, -scrollX, 0)
        } else {
            currentPage = 1
            scroller.startScroll(scrollX, 0, width - scrollX, 0)
        }
        downX = 0f
        
        invalidate()
    }
    
    private fun getScrollDistance(): Int {
        return computeHorizontalScrollOffset()
    }
    
    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            postInvalidate()
        }
    }
}