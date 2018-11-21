package com.example.myview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

abstract class MyView : View {
    var viewWidth = 0
    var viewHeight = 0

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        this.init(context!!, attrs!!)
    }

    /*
     * 初始化操作
     */
    abstract fun init(context: Context, attrs: AttributeSet)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewWidth = w - paddingLeft - paddingRight
        viewHeight = h - paddingTop - paddingBottom
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthMode == MeasureSpec.AT_MOST && layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            widthWrapContent(widthSize, heightSize)
        }
        if (heightMode == MeasureSpec.AT_MOST && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            heightWrapContent(heightSize, heightSize)
        }
    }

    /*
     * 当width为wrap_content的时候回调处理宽度
     */
    abstract fun widthWrapContent(widthSize: Int, heightSize: Int)

    /*
     * 当height为wrap_content的时候回调处理高度
     */
    abstract fun heightWrapContent(heightSize: Int, heightSize1: Int)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        drawContent(canvas)
    }

    protected abstract fun drawContent(canvas: Canvas)
}