package com.example.formylove.splash

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.*

class DaysTextView : View {
    private val mPaint = Paint()
    private val preStr = "我们在一起的"
    private val lastStr = "天"
    private var day = 0
    private val normalSize = 50F
    private val superSize = 120F

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mPaint.color = Color.WHITE
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true

        day = computeDays()
    }

    private fun computeDays(): Int {
        val start = Calendar.getInstance()
        start.apply {
            set(Calendar.YEAR, 2018)
            set(Calendar.MONTH, Calendar.JULY)
            set(Calendar.DAY_OF_MONTH, 30)
        }
        val now = Calendar.getInstance()
        val result = (now.time.time - start.time.time) / 1000 / 60 / 60 / 24
        return result.toInt() + 1
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mPaint.textSize = normalSize
        var width = mPaint.measureText(lastStr + preStr)
        mPaint.textSize = superSize
        width += mPaint.measureText("$day") + 10
        setMeasuredDimension(width.toInt(), superSize.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mPaint.textSize = normalSize
        canvas?.drawText(preStr, 0F, superSize - 10, mPaint)
        var offset = mPaint.measureText(preStr) + 5
        mPaint.textSize = superSize

        canvas?.drawText("$day", offset, superSize - 10, mPaint)
        offset += mPaint.measureText("$day") + 5

        mPaint.textSize = normalSize
        canvas?.drawText(lastStr, offset, superSize - 10, mPaint)
    }
}