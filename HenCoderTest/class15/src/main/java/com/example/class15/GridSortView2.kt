package com.example.class15

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.ViewDragHelper


class GridSortView2(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val row = 3
    private val column = 2
    
    private val dragHelper = ViewDragHelper.create(this, MyDragCallback())
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec) / column
        val heightSize = MeasureSpec.getSize(heightMeasureSpec) / row
        val mode = MeasureSpec.EXACTLY
        val childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, mode)
        val childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, mode)
        measureChildren(childWidthMeasureSpec, childHeightMeasureSpec)
        
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            val left = (measuredWidth / column.toFloat()) * (i % column)
            val top = (measuredHeight / row.toFloat()) * (i / column)
            val right = left + childWidth
            val bottom = top + childHeight
            child.layout(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        }
    }
    
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }
    
    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            postInvalidate()
        }
    }
    
    inner class MyDragCallback : ViewDragHelper.Callback() {
        private var capturedLeft = 0f
        private var capturedTop = 0f
        
        /**
         * 返回true，固定写法
         */
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }
        
        /**
         * 水平方向上的拖动处理
         * 传入的是当前拖动view的left实际值
         * 返回的view最后摆放的数值，返回0就表示不允许水平拖动
         */
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }
        
        /**
         * 水平方向上的拖动处理
         * 传入的是当前拖动view的left实际值
         * 返回的view最后摆放的数值，返回0就表示不允许竖直拖动
         */
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }
        
        /**
         * 当view的状态发生改变的时候回调这个方法
         */
        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE) {
                val view = dragHelper.capturedView
                view?.let {
                    view.elevation -= 1
                }
            }
        }
        
        /**
         * 当view呗提取起来的时候回调这个方法
         */
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation += 1
            capturedLeft = capturedChild.left.toFloat()
            capturedTop = capturedChild.top.toFloat()
        }
        
        /**
         * 当view的位置发生改变时候的回调
         */
        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
        
        }
        
        /**
         * 当手指松开的时候回调这个方法
         */
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragHelper.settleCapturedViewAt(capturedLeft.toInt(), capturedTop.toInt())
            postInvalidate()
        }
    }
}