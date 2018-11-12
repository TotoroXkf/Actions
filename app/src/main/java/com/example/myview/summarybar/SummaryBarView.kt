package com.example.myview.summarybar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.myview.MyView

class SummaryBarView : MyView {
    private lateinit var textPaint: Paint
    private lateinit var barPaint: Paint


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init() {
        textPaint = Paint()
        textPaint.isAntiAlias = true
        textPaint.textSize = 20f
        textPaint.textAlign = Paint.Align.CENTER

        barPaint = Paint()
        barPaint.isAntiAlias = true
        barPaint.color = Color.BLUE
    }

    override fun widthWrapContent(widthSize: Int, heightSize: Int) {

    }

    override fun heightWrapContent(heightSize: Int, heightSize1: Int) {

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

    }
}