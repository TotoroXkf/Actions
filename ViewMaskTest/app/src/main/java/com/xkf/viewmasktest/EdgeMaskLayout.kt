package com.xkf.viewmasktest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * author : xiakaifa
 * 2020/3/20
 */
class EdgeMaskLayout : FrameLayout {
    private val gradientColors = intArrayOf(-0x1, 0x00000000)
    private val gradientPosition = floatArrayOf(0f, 1f)

    private val leftPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rightPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val topPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bottomPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var topSize = 0f
        set(value) {
            if (value < 0) {
                field = 0f
                return
            }
            field = value
            setShader(topPaint, value)
            postInvalidate()
        }
    var bottomSize = 0f
        set(value) {
            if (value < 0) {
                field = 0f
                return
            }
            field = value
            setShader(bottomPaint, value)
            postInvalidate()
        }
    var leftSize = 0f
        set(value) {
            if (value < 0) {
                field = 0f
                return
            }
            field = value
            setShader(leftPaint, value)
            postInvalidate()
        }
    var rightSize = 0f
        set(value) {
            if (value < 0) {
                field = 0f
                return
            }
            field = value
            setShader(rightPaint, value)
            postInvalidate()
        }

    var leftMask = true
        set(value) {
            field = value
            postInvalidate()
        }
    var rightMask = true
        set(value) {
            field = value
            postInvalidate()
        }
    var bottomMask = true
        set(value) {
            field = value
            postInvalidate()
        }
    var topMask = true
        set(value) {
            field = value
            postInvalidate()
        }

    private var enableMask = true
        set(value) {
            field = value
            postInvalidate()
        }

    private var viewWidth = 0
    private var viewHeight = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        leftPaint.style = Paint.Style.FILL
        leftPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        rightPaint.style = Paint.Style.FILL
        rightPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        bottomPaint.style = Paint.Style.FILL
        bottomPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
        topPaint.style = Paint.Style.FILL
        topPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

        topSize = 20f
        bottomSize = 20f
        leftSize = 20f
        rightSize = 20f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewWidth = w
        viewHeight = h
    }

    private fun setShader(paint: Paint, size: Float) {
        val shader = LinearGradient(
            0f,
            0f,
            0f,
            size,
            gradientColors,
            gradientPosition,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
        postInvalidate()
    }

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        if (!enableMask) {
            return super.drawChild(canvas, child, drawingTime)
        }

        val layerSave = canvas.saveLayer(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            null,
            Canvas.ALL_SAVE_FLAG
        )
        val result = super.drawChild(canvas, child, drawingTime)

        // 绘制上方
        if (topMask && topSize > 0f) {
            canvas.drawRect(0f, 0f, viewWidth.toFloat(), topSize, topPaint)
        }

        // 绘制底部
        if (bottomMask && bottomSize > 0f) {
            val save = canvas.save()
            canvas.rotate(180f, viewWidth / 2f, viewHeight / 2f)
            canvas.drawRect(0f, 0f, viewWidth.toFloat(), bottomSize, bottomPaint)
            canvas.restoreToCount(save)
        }

        val offset = (viewHeight - viewWidth) / 2
        // 绘制左边
        if (leftMask && leftSize > 0f) {
            val save = canvas.save()
            canvas.rotate(90f, viewWidth / 2f, viewHeight / 2f)
            canvas.translate(0f, offset.toFloat())
            canvas.drawRect(0f - offset, 0f, viewWidth + offset.toFloat(), leftSize, leftPaint)
            canvas.restoreToCount(save)
        }

        // 绘制右边
        if (rightMask && rightSize > 0f) {
            val save = canvas.save()
            canvas.rotate(270f, viewWidth / 2f, viewHeight / 2f)
            canvas.translate(0f, offset.toFloat())
            canvas.drawRect(0f - offset, 0f, viewWidth + offset.toFloat(), rightSize, rightPaint)
            canvas.restoreToCount(save)
        }

        canvas.restoreToCount(layerSave)
        return result
    }
}