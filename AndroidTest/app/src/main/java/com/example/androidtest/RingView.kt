package com.example.androidtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class RingView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private val radius = dp2px(150f)
	private val circlePaint = Paint()
	private val textPaint = Paint()
	private val text = "大龙猫"
	private val textRect = Rect()
	private val fontMetrics: Paint.FontMetrics
	
	init {
		circlePaint.isAntiAlias = true
		circlePaint.style = Paint.Style.STROKE
		circlePaint.strokeWidth = dp2px(18f)
		circlePaint.strokeCap = Paint.Cap.ROUND
		
		textPaint.isAntiAlias = true
		textPaint.textSize = 200f
		textPaint.color = Color.RED
		textPaint.textAlign = Paint.Align.CENTER
		fontMetrics = textPaint.fontMetrics
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		textPaint.getTextBounds(text, 0, text.length, textRect)
	}
	
	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		
		// 画圆环
		circlePaint.color = Color.GRAY
		canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, circlePaint)
		circlePaint.color = Color.GREEN
		canvas.drawArc(
			width / 2 - radius,
			height / 2 - radius,
			width / 2 + radius,
			height / 2 + radius,
			-90f,
			270f,
			false,
			circlePaint
		)
		
		// 画文字,用外框的方式
		canvas.drawText(
			text,
			0,
			text.length,
			(width / 2).toFloat(),
			(height / 2).toFloat() + (textRect.bottom - textRect.top) / 2,
			textPaint
		)
		
		// 画文字，用基线的形式
		canvas.drawText(
			text,
			0,
			text.length,
			(width / 2).toFloat(),
			(height / 2).toFloat() + ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.descent),
			textPaint
		)
	}
}