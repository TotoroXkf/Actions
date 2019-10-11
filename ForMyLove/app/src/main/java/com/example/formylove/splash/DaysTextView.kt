package com.example.formylove.splash

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.formylove.utils.computeDays
import java.util.*

class DaysTextView : View {
    private val paint = Paint()
    private val preStr = "我们在一起的"
    private val lastStr = "天"
    var day = 0
    private val normalSize = 50F
    private val superSize = 120F

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        paint.textSize = normalSize
        var width = paint.measureText(lastStr + preStr)
        paint.textSize = superSize
        width += paint.measureText("$day") + 10
        setMeasuredDimension(width.toInt(), superSize.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.textSize = normalSize
        canvas?.drawText(preStr, 0F, superSize - 10, paint)
        var offset = paint.measureText(preStr) + 5
        paint.textSize = superSize

        canvas?.drawText("$day", offset, superSize - 10, paint)
        offset += paint.measureText("$day") + 5

        paint.textSize = normalSize
        canvas?.drawText(lastStr, offset, superSize - 10, paint)
    }
}