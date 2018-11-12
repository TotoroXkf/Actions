package com.example.myview.summarybar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import com.example.myview.MyView

class SummaryBarView : MyView {
    //文本绘制画笔
    private lateinit var textPaint: Paint
    //柱状图绘制画笔
    private lateinit var barPaint: Paint
    //Bar的矩形框列表
    private val barRectList = ArrayList<RectF>()
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
    //bar的数值映射为bar的高度的比例
    var ratio = 0
    //bar的最大高度
    var max = 0f

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

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (animationEnable) {
            drawWithAnimation(canvas!!)
        } else {
            drawWithoutAnimation(canvas!!)
        }
    }

    /*
     * 带动画绘制
     */
    private fun drawWithoutAnimation(canvas: Canvas) {
        //todo
    }

    /*
     * 不带动画绘制
     */
    private fun drawWithAnimation(canvas: Canvas) {
        //todo
    }

    /*
     * 在末尾添加一个bar
     */
    fun addBar(data: Float) {
        //todo
    }

    fun addBars(list: List<Float>) {
        //todo now
        if (list.isEmpty()) {
            return
        }
//        post {
//            if (dataList.isEmpty()) {
//                val start = paddingLeft + barWidth / 2
//                xCoordinate.add(start)
//                max = Math.max(max, list.max()!!)
//                if (max > viewHeight) {
//                    yCoordinate.add(viewHeight.toFloat())
//                } else {
//                }
//                //val rect = Rect(start, list[0] * ratio, start + barWidth, list[0] * ratio)
//            }
//            for () {
//
//            }
//            invalidate()
//        }
    }

    /*
     * 插入一个bar
     */
    fun insertBar(data: Float) {
        //todo
    }

    /*
     * 删除一个bar
     */
    fun removeBar(index: Int) {
        //todo
    }

    /*
     * 清空所有的bar
     */
    fun removeAll() {
        //todo
    }

    private fun coordinateAdajust() {
        post {

        }
    }
}