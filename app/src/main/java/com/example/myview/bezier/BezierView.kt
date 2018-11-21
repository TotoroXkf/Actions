package com.example.myview.bezier

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import com.example.myview.MyView

class BezierView : MyView {

    val startPoint = Point()
    val endPoint = Point()
    val controlPoiont = Point()

    val startRect = Rect()
    val endRect = Rect()
    val controlRect = Rect()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init(context: Context, attrs: AttributeSet) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun widthWrapContent(widthSize: Int, heightSize: Int) {

    }

    override fun heightWrapContent(heightSize: Int, heightSize1: Int) {

    }

    @SuppressLint("ClickableViewAccessibility")

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {

            }

            MotionEvent.ACTION_MOVE -> {

            }

            MotionEvent.ACTION_UP -> {

            }

        }
        return true
    }

    override fun drawContent(canvas: Canvas) {

    }
}