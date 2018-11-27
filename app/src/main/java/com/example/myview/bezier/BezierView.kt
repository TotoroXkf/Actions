package com.example.myview.bezier

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.example.myview.MyView

class BezierView : MyView {

    private var startX = 0f
    private var startY = 0f
    private var endX = 0f
    private var endY = 0f
    private var controlX = 0f
    private var controlY = 0f

    private lateinit var startRect: RectF
    private lateinit var endRect: RectF
    private lateinit var controlRect: RectF

    private val path = Path()

    private val startSelected = 1
    private val controlSelected = 2
    private val endSelected = 3
    private val none = 0
    private var state = none


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init(context: Context, attrs: AttributeSet) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        isClickable = true

        startRect = RectF(200f, 500f, 250f, 550f)
        startX = (startRect.left + startRect.right) / 2
        startY = (startRect.top + startRect.bottom) / 2


        controlRect = RectF(500f, 300f, 550f, 350f)
        controlX = (controlRect.left + controlRect.right) / 2
        controlY = (controlRect.top + controlRect.bottom) / 2

        endRect = RectF(800f, 500f, 850f, 550f)
        endX = (endRect.left + endRect.right) / 2
        endY = (endRect.top + endRect.bottom) / 2

    }

    override fun widthWrapContent(widthSize: Int, heightSize: Int) {

    }

    override fun heightWrapContent(heightSize: Int, heightSize1: Int) {

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val result = super.onTouchEvent(event)
        if (event == null) {
            return result
        }
        val curX = event.x
        val curY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                state = when {
                    startRect.contains(curX, curY) -> startSelected
                    controlRect.contains(curX, curY) -> controlSelected
                    endRect.contains(curX, curY) -> endSelected
                    else -> none
                }
            }

            MotionEvent.ACTION_MOVE -> {
                when (state) {
                    startSelected -> {
                        startX = curX
                        startY = curY
                        rectMove(startRect, curX, curY)
                        invalidate()
                    }
                    controlSelected -> {
                        controlX = curX
                        controlY = curY
                        rectMove(controlRect, curX, curY)
                        invalidate()
                    }
                    endSelected -> {
                        endX = curX
                        endY = curY
                        rectMove(endRect, curX, curY)
                        invalidate()
                    }
                    none -> {

                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                if (state != none) {
                    Log.e("xkf123456789", "当前点信息 ---------------------------------------------------")
                    Log.e("xkf123456789", "起始点 => ($startX,$startY)")
                    Log.e("xkf123456789", "控制点 => ($controlX,$controlY)")
                    Log.e("xkf123456789", "结束点 => ($endX,$endY)")
                    Log.e("xkf123456789", "--------------------------------------------------------------")
                }
            }
        }
        return true
    }

    /*
     * 根据中心点重置矩阵的位置
     */
    private fun rectMove(rect: RectF, centerX: Float, centerY: Float) {
        val rectWidth = rect.width()
        val rectHeight = rect.height()
        val l = centerX - rectWidth / 2
        val t = centerY - rectHeight / 2
        val r = centerX + rectWidth / 2
        val b = centerY + rectHeight / 2
        rect.set(l, t, r, b)
    }

    override fun drawContent(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawOval(startRect, paint)
        canvas.drawOval(controlRect, paint)
        canvas.drawOval(endRect, paint)

        path.reset()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        path.moveTo(startX, startY)
        path.quadTo(controlX, controlY, endX, endY)
        canvas.drawPath(path, paint)
    }
}