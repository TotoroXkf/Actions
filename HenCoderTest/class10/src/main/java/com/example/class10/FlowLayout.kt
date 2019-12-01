package com.example.class10

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class FlowLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val childRectList = mutableListOf<Rect>()
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        childRectList.clear()
        for (i in 0 until childCount) {
            childRectList.add(Rect())
        }
        
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        
        var widthUsed = 0
        var lineMaxWidth = 0
        var heightUsed = 0
        var lineMaxHeight = 0
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val rect = childRectList[i]
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, 0)
            // 换行
            if (widthUsed + childView.measuredWidth >= widthSize) {
                lineMaxWidth = max(lineMaxWidth, widthUsed)
                widthUsed = 0
                
                heightUsed += lineMaxHeight
                lineMaxHeight = 0
            }
            rect.set(
                widthUsed,
                heightUsed,
                widthUsed + childView.measuredWidth,
                heightUsed + childView.measuredHeight
            )
            widthUsed += childView.measuredWidth
            lineMaxHeight = max(lineMaxHeight, childView.measuredHeight)
        }
        
        lineMaxWidth = max(lineMaxWidth, widthUsed)
        if (lineMaxHeight > 0) {
            heightUsed += lineMaxHeight
        }
        setMeasuredDimension(lineMaxWidth, heightUsed)
    }
    
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val rect = childRectList[i]
            val childView = getChildAt(i)
            childView.layout(rect.left, rect.top, rect.right, rect.bottom)
        }
    }
    
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}