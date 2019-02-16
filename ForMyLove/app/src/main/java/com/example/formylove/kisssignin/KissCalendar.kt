package com.example.formylove.kisssignin

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.util.*

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
		
		while (paint.measureText("10") < (rectLength / 3)) {
			paint.textSize += 1
		}
	}
	
	private fun moveRectOneStep() {
		val left = rect.left
		val top = rect.top
		val right = rect.right
		val bottom = rect.bottom
		
		if (Math.abs(viewWidth - right) > 5) {
			rect.set(left + rectLength, top, right + rectLength, bottom)
		} else {
			rect.set(0f, bottom, rectLength, bottom + rectLength)
		}
	}
	
	private fun moveRectToPosition(start: Int, dayOfMonth: Int, dayOfWeek: Int) {
		val row = (start + dayOfMonth + 7) / 7
		//todo
	}
	
	override fun onDraw(canvas: Canvas?) {
		if (canvas == null) {
			return
		}
		rect.set(0f, 0f, rectLength, rectLength)
		drawWeek(canvas)
		drawDay(canvas)
	}
	
	private fun drawWeek(canvas: Canvas) {
		val week = arrayOf("一", "二", "三", "四", "五", "六", "天")
		paint.textSize -= 1
		for (text in week) {
			drawTextInRect(text, canvas)
			moveRectOneStep()
		}
	}
	
	private fun drawDay(canvas: Canvas) {
		val calendar = Calendar.getInstance()
		while (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1)
		}
		val weekMap = hashMapOf(
				Calendar.MONDAY to 0,
				Calendar.TUESDAY to 1,
				Calendar.WEDNESDAY to 2,
				Calendar.THURSDAY to 3,
				Calendar.FRIDAY to 4,
				Calendar.SATURDAY to 5,
				Calendar.SUNDAY to 6
		)
		val steps = weekMap[calendar.get(Calendar.DAY_OF_WEEK)]!!
		for (i in 0 until steps) {
			moveRectOneStep()
		}
		val month = calendar.get(Calendar.MONTH)
		while (month == calendar.get(Calendar.MONTH)) {
			val loveShape = LoveShape(rect)
			loveShape.drawLove(canvas)
			drawTextInRect("" + calendar.get(Calendar.DAY_OF_MONTH), canvas)
			moveRectOneStep()
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
		}
	}
	
	private fun drawTextInRect(text: String, canvas: Canvas) {
		val rectCenterX = (rect.right - rect.left) / 2 + rect.left
		val rectCenterY = (rect.bottom - rect.top) / 2 + rect.top
		val textWidth = paint.measureText(text)
		val textHeight = getTextHeight()
		canvas.drawText(text, rectCenterX - textWidth / 2, rectCenterY + textHeight / 2 - 5f, paint)
	}
	
	private fun getTextHeight(): Float {
		val fontMetrics = paint.fontMetrics
		return fontMetrics.bottom - fontMetrics.top
	}
}