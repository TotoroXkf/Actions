package com.example.androidtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private val paint = Paint()
	private val rect = RectF()
	private val length = dp2px(150f)
	private val angles = arrayOf(
		60f,
		120f,
		45f,
		95f,
		40f
	)
	private val colors = arrayOf(
		Color.BLACK,
		Color.RED,
		Color.YELLOW,
		Color.BLUE,
		Color.GREEN
	)
	private val pullIndex = 1
	private val offset = dp2px(20f)
	
	
	init {
		paint.isAntiAlias = true
		paint.style = Paint.Style.FILL
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		
		rect.set(
			(w / 2 - length / 2),
			(h / 2 - length / 2),
			(w / 2 + length / 2),
			(h / 2 + length / 2)
		)
	}
	
	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		
		var currentAngle = 0f
		for (i in angles.indices) {
			paint.color = colors[i]
			if (i == pullIndex) {
				canvas.save()
				canvas.translate(
					(offset * cos(Math.toRadians((currentAngle + angles[i] / 2).toDouble()))).toFloat(),
					(offset * sin(Math.toRadians((currentAngle + angles[i] / 2).toDouble()))).toFloat()
				)
				canvas.drawArc(rect, currentAngle, angles[i], true, paint)
				currentAngle += angles[i]
				canvas.restore()
				continue
			}
			canvas.drawArc(rect, currentAngle, angles[i], true, paint)
			currentAngle += angles[i]
		}
	}
}