package com.example.myview.summarybar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import com.example.myview.MyView

class SummaryBarView : MyView {
    private lateinit var textPaint: Paint
    private lateinit var barPaint: Paint

    //Bar列表
    private val barList = ArrayList<Bar>()
    //柱状图高度列表
    private val dataList = ArrayList<Float>()
    //底下文字列表
    private val textList = ArrayList<String>()
    //柱状图的横坐标
    private val xCoordinate = ArrayList<Float>()
    //柱状图的纵坐标
    private val yCoordinate = ArrayList<Float>()
    //绘制时显示动画，默认为true
    var animationEnable = true
    //每个Bar之间的间隔
    var space = 0.0f
    //每个Bar的宽度
    var barWidth = 0.0f


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init(context: Context, attrs: AttributeSet) {
        textPaint = Paint()
        textPaint.isAntiAlias = true
        textPaint.textSize = 20f
        textPaint.textAlign = Paint.Align.CENTER

        barPaint = Paint()
        barPaint.isAntiAlias = true
        barPaint.color = Color.BLUE

        //间隔默认为8dp
        space = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics)
        //bar的宽度默认为8dp
        barWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f, resources.displayMetrics)
    }

    override fun widthWrapContent(widthSize: Int, heightSize: Int) {

    }

    override fun heightWrapContent(heightSize: Int, heightSize1: Int) {

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        //计算最左边的
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (animationEnable) {
            drawWithAnimation(canvas!!)
        } else {
            drawWithoutAnimation(canvas!!)
        }
    }

    private fun drawWithoutAnimation(canvas: Canvas) {

    }

    private fun drawWithAnimation(canvas: Canvas) {

    }

    private inner class Bar {
        //绘制柱状条的四个位置
        var left: Int = 0
        var top: Int = 0
        var right: Int = 0
        var bottom: Int = 0

        fun isInside(x: Float, y: Float): Boolean {
            return x > left && x < right && y > top && y < bottom
        }
    }
}