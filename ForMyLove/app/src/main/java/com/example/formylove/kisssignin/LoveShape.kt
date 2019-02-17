package com.example.formylove.kisssignin

import android.graphics.*
import kotlin.math.sqrt
import android.R.attr.path


class LoveShape(private val rect: RectF) {
	private val path = Path()
	private val paint = Paint()
	
	init {
		paint.isAntiAlias = true
		paint.color = Color.rgb(254, 57, 63)
	}
	
	fun setRect(rectF: RectF) {
		rect.set(rectF)
	}
	
	fun drawLoveSolid(canvas: Canvas) {
	
	}
	
	
	fun drawLove(canvas: Canvas) {
		path.reset()
		val width = rect.right - rect.left
		val height = rect.bottom - rect.top
		path.moveTo(rect.left + width / 2, rect.top + height / 4)
		path.cubicTo(rect.left + width * 6 / 7, rect.top,
				rect.right + width / 5, rect.top + height / 4, rect.left + width / 2, rect.bottom)
		path.moveTo(rect.left + width / 2, rect.top + height / 4)
		path.cubicTo(rect.left + width / 7, rect.top,
				rect.left - width / 5, rect.top + height / 4, rect.left + width / 2, rect.bottom)
		canvas.drawPath(path, paint)
	}
}