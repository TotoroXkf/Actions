package com.example.androidtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private val paint1 = Paint()
	private val paint2 = Paint()
	private val sweepAngle = 240f
	private val startAngle = 150f
	private val radius = dp2px(100f)
	private val rectF = RectF()
	private val arcPath = Path()
	
	init {
		paint1.isAntiAlias = true
		paint1.style = Paint.Style.STROKE
		paint1.strokeWidth = 10f
		
		paint2.isAntiAlias = true
		paint2.style = Paint.Style.STROKE
		paint2.strokeWidth = 10f
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		
		rectF.set(w / 2 - radius, h / 2 - radius, w / 2 + radius, h / 2 + radius)
		
		arcPath.reset()
		arcPath.addArc(rectF, startAngle, sweepAngle)
		
		val dashPath = Path()
		dashPath.addRect(0f, 0f, dp2px(2f), dp2px(8f), Path.Direction.CW)
		val pathMeasure = PathMeasure(arcPath, false)
		val length = pathMeasure.length
		val pathDashPathEffect = PathDashPathEffect(
			dashPath,
			length / 20,
			0f,
			PathDashPathEffect.Style.ROTATE
		)
		paint2.pathEffect = pathDashPathEffect
	}
	
	override fun onDraw(canvas: Canvas) {
		// 外圈圆盘
		canvas.drawPath(arcPath, paint1)
		
		// 刻度
		canvas.drawPath(arcPath, paint2)
		
		//画线
		canvas.drawLine(
			(width / 2).toFloat(),
			(height / 2).toFloat(),
			(width / 2).toFloat() + dp2px(50f) * cos(Math.toRadians(240.toDouble())).toFloat(),
			(height / 2).toFloat() + dp2px(50f) * sin(Math.toRadians(240.toDouble())).toFloat(),
			paint1
		)
	}
}