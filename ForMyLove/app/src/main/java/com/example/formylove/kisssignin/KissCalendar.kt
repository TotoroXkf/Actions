package com.example.formylove.kisssignin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

class KissCalendar : View {
	private var viewWidth = 0f
	private var viewHeight = 0f
	private val paint = Paint()
	
	private val rect = RectF(0f, 0f, 0f, 0f)
	private var rectLength = 0f
	
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	init {
		paint.isAntiAlias = true
		paint.color = Color.BLACK
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		viewWidth = w.toFloat()
		viewHeight = w.toFloat()
		rectLength = viewWidth / 7
		rect.set(0f, 0f, rectLength, rectLength)
	}
	
	private fun moveRect() {
		val left = rect.left
		val top = rect.top
		val right = rect.right
		val bottom = rect.bottom
		
		if (Math.abs(viewWidth - right + rectLength) > 5) {
			rect.set(left + rectLength, top, right + rectLength, bottom)
		} else {
			rect.set(0f, bottom, rectLength, bottom + rectLength)
		}
	}
	
	override fun onDraw(canvas: Canvas?) {
		if (canvas == null) {
			return
		}
		drawWeek(canvas)
		drawDay(canvas)
	}
	
	private fun drawWeek(canvas: Canvas) {
		val week = arrayOf("一", "二", "三", "四", "五", "六", "天")
		while (paint.measureText(week[0]) < (rectLength / 3)) {
			paint.textSize += 1
		}
		paint.textSize -= 1
		for (text in week) {
			val rectCenterX = (rect.right - rect.left) / 2 + rect.left
			val rectCenterY = (rect.bottom - rect.top) / 2 + rect.top
			val textWidth = paint.measureText(text)
			val textHeight = getTextHeight(text)
			canvas.drawText(text, rectCenterX - textWidth / 2, rectCenterY + textHeight / 2 - 5f, paint)
			moveRect()
		}
	}
	
	private fun drawDay(canvas: Canvas) {
	
	}
	
	private fun getTextHeight(text: String): Float {
		val fontMetrics = paint.fontMetrics
		return fontMetrics.bottom - fontMetrics.top
	}
}