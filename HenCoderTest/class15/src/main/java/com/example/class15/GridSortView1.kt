package com.example.class15

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup

/**
 * 基于DragListener的拖动表格排序
 */
class GridSortView1(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs),
    View.OnDragListener {
    private val row = 3
    private val column = 2
    private var dragView = View(context)
    private val viewMap = HashMap<View, Int>()
    
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
            viewMap[child] = i
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            val left = (measuredWidth / column.toFloat()) * (i % column)
            val top = (measuredHeight / row.toFloat()) * (i / column)
            val right = left + childWidth
            val bottom = top + childHeight
            child.layout(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
        }
    }
    
    @TargetApi(Build.VERSION_CODES.N)
    override fun onFinishInflate() {
        super.onFinishInflate()
        
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.setOnLongClickListener {
                dragView = child
                // 拖拽的启动代码
                // 第一个参数是传递的数据
                // 第二个参数是提取的view的一个绘制，一般来说用这个系统的就可以
                // 第三个是传递到拖拽事件的数据，传递view本身即可
                child.startDragAndDrop(null, DragShadowBuilder(child), child, 0)
                return@setOnLongClickListener false
            }
            child.setOnDragListener(this)
        }
    }
    
    override fun onDrag(v: View, event: DragEvent): Boolean {
        when (event.action) {
            // 开始拖动
            DragEvent.ACTION_DRAG_STARTED -> {
                if (event.localState == v) {
                    v.visibility = View.INVISIBLE
                }
            }
            // 移动过程中，此时的 v 参数代表的是被放置的view，而不是被拖动的view
            DragEvent.ACTION_DRAG_ENTERED -> {
                if (event.localState != v) {
                    sort(v)
                }
            }
            // 拖动释放
            DragEvent.ACTION_DRAG_ENDED -> {
                if (event.localState == v) {
                    v.visibility = View.VISIBLE
                }
            }
        }
        return true
    }
    
    private fun sort(enterView: View) {
        if (enterView == dragView) {
            return
        }
        val viewWidth = dragView.width
        val viewHeight = dragView.height
        val fromIndex = viewMap[dragView]!!
        val toIndex = viewMap[enterView]!!
        
        dragView.x = ((toIndex % column) * viewWidth).toFloat()
        dragView.y = ((toIndex / column) * viewHeight).toFloat()
        val left = (fromIndex % column) * viewWidth
        val top = (fromIndex / column) * viewHeight
        enterView.animate().x(left.toFloat()).y(top.toFloat()).start()
        
        viewMap[enterView] = fromIndex
        viewMap[dragView] = toIndex
    }
}