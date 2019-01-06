package com.example.myclock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ClockView : View {
    private var viewWidth = 0
    private var viewHeight = 0
    private var paint = Paint()

    private val HMS = 0
    private val HM = 1
    private var currentType = 1


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun init() {
        paint.color = Color.WHITE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
    }

    fun setTypre(type: Int) {
        currentType = type
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        if (currentType == HMS) {
            drawHMS(canvas)
        } else if (currentType == HM) {
            drawHM(canvas)
        }
    }

    private fun drawHM(canvas: Canvas) {

    }

    private fun drawHMS(canvas: Canvas) {

    }

}