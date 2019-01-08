package com.example.myclock

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import java.util.*

class ClockView : View {
	private var viewWidth = 0f
	private var viewHeight = 0f
	private var paint = Paint()
	
	private val HMS = 0
	private val HM = 1
	private var currentType = 1
	
	private var alphaValue = 255
	private var color = Color.BLACK
	
	private lateinit var gestureDetector: GestureDetector
	
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
		init()
	}
	
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
		context,
		attrs,
		defStyleAttr
	)
	
	private fun init() {
		paint.color = Color.WHITE
		paint.textSize = 350f
		paint.isAntiAlias = true
		
		gestureDetector = GestureDetector(context, onGestureListener)
		
		val symbolAnimation = ValueAnimator.ofInt(255, 1, 255)
		symbolAnimation.apply {
			duration = 3000
			repeatCount = Animation.INFINITE
			repeatMode = ValueAnimator.RESTART
			addUpdateListener {
				alphaValue = it.animatedValue as Int
				invalidate()
			}
		}
		val colorAnimation = ValueAnimator.ofArgb(
			Color.BLACK, Color.RED, Color.GREEN, Color.BLUE
		)
		colorAnimation.apply {
			duration = 20000
			repeatCount = Animation.INFINITE
			repeatMode = ValueAnimator.REVERSE
			addUpdateListener {
				color = it.animatedValue as Int
			}
		}
		val animationSet = AnimatorSet()
		animationSet.playTogether(symbolAnimation, colorAnimation)
		animationSet.start()
	}
	
	private var onGestureListener = object : GestureDetector.SimpleOnGestureListener() {
		override fun onDown(e: MotionEvent?): Boolean {
			return true
		}
		
		override fun onDoubleTap(e: MotionEvent?): Boolean {
			currentType = if (currentType == HM) {
				HMS
			} else {
				HM
			}
			invalidate()
			return true
		}
	}
	
	override fun onTouchEvent(event: MotionEvent?): Boolean {
		return gestureDetector.onTouchEvent(event)
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		viewWidth = w.toFloat()
		viewHeight = h.toFloat()
	}
	
	override fun onDraw(canvas: Canvas?) {
		super.onDraw(canvas)
		if (canvas == null) {
			return
		}
		paint.alpha = 255
		canvas.drawColor(color)
		
		if (currentType == HMS) {
			drawHMS(canvas)
		} else if (currentType == HM) {
			drawHM(canvas)
		}
	}
	
	private fun drawHM(canvas: Canvas) {
		val hour = getHour()
		val minute = getMinute()
		
		val radius = 50f
		val left = viewWidth / 2 - radius / 2
		val top = viewHeight / 2 - radius / 2
		val right = left + radius
		val bottom = top + radius
		val rect = RectF(left, top - radius, right, bottom - radius)
		paint.alpha = alphaValue
		canvas.drawOval(rect, paint)
		rect.set(left, top + radius, right, bottom + radius)
		canvas.drawOval(rect, paint)
		
		paint.alpha = 255
		val hourWidth = paint.measureText(hour)
		val baseY = getBaseY()
		val space = 15f
		canvas.drawText(hour, viewWidth / 2 - hourWidth - radius / 2 - space, baseY, paint)
		canvas.drawText(minute, viewWidth / 2 + radius / 2 + space, baseY, paint)
	}
	
	private fun drawHMS(canvas: Canvas) {
		//todo 画时分秒
		val hour = getHour()
		val minute = getMinute()
		val second = getSecond()
	}
	
	private fun getBaseY(): Float {
		var baseY = viewHeight / 2
		val metrics = paint.fontMetrics
		val top = baseY + metrics.top
		val bottom = +baseY + metrics.bottom
		
		baseY -= (bottom - baseY)
		baseY += ((bottom - top)) / 2
		return baseY
	}
	
	private fun getHour(): String {
		val calendar = Calendar.getInstance()
		val hour = calendar.get(Calendar.HOUR_OF_DAY)
		return if (hour < 10) "0$hour" else hour.toString()
	}
	
	private fun getMinute(): String {
		val calendar = Calendar.getInstance()
		val minute = calendar.get(Calendar.MINUTE)
		return if (minute < 10) "0$minute" else minute.toString()
	}
	
	private fun getSecond(): String {
		val calendar = Calendar.getInstance()
		val second = calendar.get(Calendar.SECOND)
		return if (second < 10) "0$second" else second.toString()
	}
	
	
}