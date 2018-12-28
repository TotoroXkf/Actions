package com.example.formylove.timeline

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.util.TypedValue


open class TimeLineItemView : View {
    private val circlePaint = Paint()
    private var circleRadius = 0f
    private var ringRadius = 0f

    private val textPaint = Paint()
    private var titleTextSize = 0f
    private var contentTextSize = 0f

    private var viewWidth = 0f
    private var viewHeight = 0f

    private var titleText = ""
    private var contentText = ""
    private var type = 0
    private val lineText = ArrayList<String>()

    //最上面和下方圆环的间隔距离
    private var space = 0f
    //圆环和左右文字的距离以及view最左边和view最右边的距离
    private var betweenSpace = 0f

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.rgb(0xFF, 0xB3, 0xCB)

        textPaint.isAntiAlias = true

        titleTextSize = sp2px(18)
        contentTextSize = sp2px(15)

        circleRadius = dp2px(10)
        ringRadius = dp2px(15)

        space = dp2px(8)
        betweenSpace = dp2px(8)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        //计算出可以显示文字的宽度。
        //等于整个屏幕的宽度减去圆环的直径，左右和中间的空白，然后除2得到一边的宽度
        val textAreaSize = (widthSize - ringRadius * 2 - betweenSpace * 2) / 2
        lineText.clear()
        //切割文字
        var start = 0
        textPaint.textSize = contentTextSize
        var i = 0
        while (i < contentText.length) {
            if (contentText[i] == '@') {
                lineText.add(contentText.substring(start, i))
                start = i + 1
                i += 1
                continue
            }
            val size = textPaint.measureText(contentText, start, i)
            if (size > textAreaSize) {
                lineText.add(contentText.substring(start, i - 1))
                start = i - 1
            } else {
                i += 1
            }
        }
        if (start < i) {
            lineText.add(contentText.substring(start, i))
        }

        //计算整体的高度
        //计算方法如下
        //日期标题的高度+中间的间隔+（每一行文字*textSize）+ 上面的间隔+下面的间隔
        var heightSize = 0f
        heightSize += space * 3 // 上下和中间的间隔
        heightSize += titleTextSize // 日期标题的高度
        heightSize += lineText.size * contentTextSize// 若干行文字的高度
        heightSize = Math.max(heightSize, space * 2 + ringRadius)

        setMeasuredDimension(widthSize, heightSize.toInt())

        //记录View的宽高
        viewWidth = measuredWidth.toFloat()
        viewHeight = measuredHeight.toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        //画圆和圆环
        circlePaint.style = Paint.Style.FILL
        canvas.drawCircle(viewWidth / 2, ringRadius + space, circleRadius, circlePaint)
        circlePaint.style = Paint.Style.STROKE
        canvas.drawCircle(viewWidth / 2, ringRadius + space, ringRadius, circlePaint)

        //绘制上下的线
        canvas.drawLine(viewWidth / 2, space, viewWidth / 2, 0f, circlePaint)
        canvas.drawLine(viewWidth / 2, space + ringRadius * 2, viewWidth / 2, viewHeight, circlePaint)

        //确定左右两边文字的起始坐标
        val leftTextStartX = space
        val leftTextStartY = space + titleTextSize
        val rightTextStartX = viewWidth / 2 + ringRadius + betweenSpace
        val rightTextStartY = space + titleTextSize

        //绘制日期文字
        textPaint.color = Color.BLACK
        textPaint.textSize = titleTextSize
        if (type == 0) {
            canvas.drawText(titleText, rightTextStartX, rightTextStartY, textPaint)
        } else {
            canvas.drawText(titleText, leftTextStartX, leftTextStartY, textPaint)
        }

        //绘制逐行的文字
        textPaint.textSize = contentTextSize
        var offset = space + contentTextSize
        for (text in lineText) {
            if (type == 0) {
                canvas.drawText(text, rightTextStartX, rightTextStartY + offset, textPaint)
            } else {
                canvas.drawText(text, leftTextStartX, leftTextStartY + offset, textPaint)
            }
            offset += contentTextSize
        }

    }

    /*
     * 设置数据源
     * 时间，事件，和显示在左边还是右边
     */
    fun setData(timeLise: TimeLine, type: Int) {
        titleText = timeLise.date!!
        contentText = timeLise.thing!!
        this.type = type
        requestLayout()
    }

    /*
     * dp转换为px
     */
    private fun dp2px(dp: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)
    }

    /*
     * sp转换为px
     */
    private fun sp2px(sp: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), resources.displayMetrics)
    }
}
