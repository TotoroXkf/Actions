package com.example.myclock

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*
import kotlin.math.min

class ClockView : View {
	private var viewWidth = 0
	private var viewHeight = 0
	private var paint = Paint()
	
	private val HMS = 0
	private val HM = 1
	private var currentType = 1
	
	private var alphaValue = 1.0f
	
	private lateinit var calendar: Calendar
	
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
		init()
	}
	
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	
	private fun init() {
		calendar = Calendar.getInstance()
		
		paint.color = Color.WHITE
		paint.textSize = 350f
		paint.isAntiAlias = true
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		viewWidth = w
		viewHeight = h
	}
	
	fun setTypre(type: Int) {
		currentType = type
		invalidate()
	}
	
	override fun onDraw(canvas: Canvas?) {
		super.onDraw(canvas)
		if (canvas == null) {
			return
		}
		if (currentType == HMS) {
			drawHMS(canvas)
		} else if (currentType == HM) {
			drawHM(canvas)
		}
	}
	
	private fun drawHM(canvas: Canvas) {
		val hour = getHour()
		val minute = getMinute()
		
		val hourWidth = paint.measureText(hour)
		val minuteWidth = paint.measureText(hour)
		val offsetY = computeOffsetY()
		canvas.drawText(hour, viewWidth / 2 - hourWidth, viewHeight / 2 + offsetY, paint)
		canvas.drawText(minute, viewWidth / 2 + 15f, viewHeight / 2 + offsetY, paint)
	}
	
	private fun drawHMS(canvas: Canvas) {
	
	}
	
	private fun computeOffsetY(): Float {
		val baseY = viewHeight / 2
		val metrics = paint.fontMetrics
		val ascent = baseY + metrics.ascent
		return (baseY - ascent) / 2 - 25f
	}
	
	private fun getHour(): String {
		val hour = calendar.get(Calendar.HOUR_OF_DAY)
		return if (hour < 10) "0$hour" else hour.toString()
	}
	
	private fun getMinute(): String {
		val minute = calendar.get(Calendar.MINUTE)
		return if (minute < 10) "0$minute" else minute.toString()
	}
	
	private fun getSecond(): String {
		val second = calendar.get(Calendar.SECOND)
		return if (second < 10) "0$second" else second.toString()
	}
}