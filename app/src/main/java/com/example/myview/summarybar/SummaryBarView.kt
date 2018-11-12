package com.example.myview.summarybar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
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
    //数值高度映射的象素点数
    var ratio = 0.0f

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
        //todo now

    }

    /*
     * 在末尾添加一个bar
     */
    fun addBar(data: Float, text: String) {
        //todo
    }

    /*
     * 一次性添加多个Bar
     */
    fun addBars(newDataList: List<Float>, newTextList: List<String>) {
        if (newDataList.isEmpty()) {
            return
        }
        dataList.addAll(newDataList)
        textList.addAll(newTextList)
        coordinateAdjust()
    }

    /*
     * 插入一个bar
     */
    fun insertBar(data: Float, text: String) {
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

    /*
     * 按照数值高度重新调整坐标
     * 所有外界的改动都是改动在dataList上面的，所以在调整的时候按照dataList为标准修改
     */
    private fun coordinateAdjust() {
        xCoordinate.clear()
        yCoordinate.clear()
        barRectList.clear()

        //post保证一定是在测量完成后执行
        post {
            val start = paddingLeft + space / 2

            val max = dataList.max()!!
            ratio = (viewHeight / max) * 0.75f
            for (i in dataList.indices) {
                //确定当前bar的起始横纵坐标，也就是位于左下角的坐标
                xCoordinate.add(start + i * (space + barWidth))
                yCoordinate.add(viewHeight - 25f - paddingBottom)

                //计算bar的矩形框
                val left = xCoordinate[i]
                val top = yCoordinate[i] - dataList[i] * ratio
                val right = xCoordinate[i] + barWidth
                val bottom = yCoordinate[i]
                val rectF = RectF(left, top, right, bottom)
                barRectList.add(rectF)
            }
        }
    }
}