package com.example.myview.summarybar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import com.example.myview.MyView

class SummaryBarView : MyView {

    //每个bar的横纵坐标
    val positionX = ArrayList<Float>()
    val positionY = 0f
    //数据源
    val data = ArrayList<Float>()
    //点击显示的文字源
    val textList = ArrayList<String>()
    //传入数据与实际绘制像素点的比值
    val ratio = 1f

    val barPaint = Paint()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init(context: Context, attrs: AttributeSet) {
        barPaint.isAntiAlias = true
        barPaint.color = Color.rgb(0x39, 0xc5, 0xbb)
    }

    override fun widthWrapContent(widthSize: Int, heightSize: Int) {

    }

    override fun heightWrapContent(heightSize: Int, heightSize1: Int) {

    }

    override fun drawContent(canvas: Canvas) {

    }
}