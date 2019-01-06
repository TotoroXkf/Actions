package com.example.myclock

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class ClockView : View {
    private var viewWidth = 0
    private var viewHight = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun init() {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}