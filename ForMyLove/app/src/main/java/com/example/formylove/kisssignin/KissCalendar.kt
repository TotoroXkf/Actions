package com.example.formylove.kisssignin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashMap

//todo 动画会遮住字
//todo 连续点击动画暂停

class KissCalendar : View {
	private var viewWidth = 0f
	private var viewHeight = 0f
	private val paint = Paint()
	private val rect = RectF(0f, 0f, 0f, 0f)
	private var rectLength = 0f
	
	private var startDayOfWeek = 1
	private var today = 1
	private val loveShape = LoveShape(RectF())
	
	private val daySet = HashSet<Int>()
	private val clickDays = LinkedHashMap<Int, RectF>()
	private val animatorMap = HashMap<ValueAnimator, Long>()
	
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
		paint.textSize -= 1
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
	
	private fun getStartBlock() = 6 + startDayOfWeek
	
	override fun onTouchEvent(event: MotionEvent?): Boolean {
		val result = super.onTouchEvent(event)
		if (event == null) {
			return result
		}
		when (event.action) {
			MotionEvent.ACTION_DOWN -> {
				return true
			}
			MotionEvent.ACTION_UP -> {
				val x = event.x
				val y = event.y
				//取值从0到6
				val row = (y / rectLength).toInt()
				val col = (x / rectLength).toInt()
				if (row == 0) {
					return result
				}
				val block = row * 7 + col
				val startBlock = getStartBlock()
				val day = block - startBlock + 1
				if (day > today || day in daySet || day in clickDays) {
					return result
				}
				//daySet.add(day)
				//todo 直接添加到DaySet和动画产生违和
				val centerX = col.toFloat() * rectLength + rectLength / 2
				val centerY = row.toFloat() * rectLength + rectLength / 2
				clickDays[day] = RectF()
				val valueAnimator = ValueAnimator.ofFloat(0f, 0.5f)
				valueAnimator.duration = 1000
				valueAnimator.addUpdateListener {
					val value = (it.animatedValue) as Float
					val animatorRect = clickDays[day]!!
					animatorRect.set(centerX - rectLength * value,
							centerY - rectLength * value,
							centerX + rectLength * value,
							centerY + rectLength * value)
					invalidate()
				}
//				valueAnimator.addListener(object : AnimatorListenerAdapter() {
//					override fun onAnimationEnd(animation: Animator?) {
//						animatorList.remove(valueAnimator)
//						if (animatorList.isNotEmpty()) {
//							animatorList.first().start()
//						}
//					}
//				})
//				valueAnimator.currentPlayTime = 500
//				if (animatorList.size == 1) {
//					valueAnimator.start()
//				}
//				valueAnimator.start()
			}
			else -> {
			
			}
		}
		return true
	}
	
	override fun onDraw(canvas: Canvas?) {
		if (canvas == null) {
			return
		}
		rect.set(0f, 0f, rectLength, rectLength)
		drawWeek(canvas)
		drawDay(canvas)
		drawDayAnimation(canvas)
	}
	
	private fun drawWeek(canvas: Canvas) {
		val week = arrayOf("一", "二", "三", "四", "五", "六", "天")
		paint.color = Color.BLACK
		for (text in week) {
			drawTextInRect(text, canvas)
			moveRectOneStep()
		}
	}
	
	private fun drawDay(canvas: Canvas) {
		val calendar = Calendar.getInstance()
		today = calendar.get(Calendar.DAY_OF_MONTH)
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
		startDayOfWeek = weekMap[calendar.get(Calendar.DAY_OF_WEEK)]!! + 1
		val steps = weekMap[calendar.get(Calendar.DAY_OF_WEEK)]!!
		for (i in 0 until steps) {
			moveRectOneStep()
		}
		val month = calendar.get(Calendar.MONTH)
		while (month == calendar.get(Calendar.MONTH)) {
			loveShape.setRect(rect)
			paint.color = Color.BLACK
			
			val day = calendar.get(Calendar.DAY_OF_MONTH)
			if (day in daySet) {
				loveShape.drawLoveSolid(canvas)
				paint.color = Color.WHITE
			} else if (day <= today) {
				loveShape.drawLoveHollow(canvas)
			}
			
			drawTextInRect("" + calendar.get(Calendar.DAY_OF_MONTH), canvas)
			moveRectOneStep()
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
		}
	}
	
	private fun drawDayAnimation(canvas: Canvas) {
		for ((day, animatorRect) in clickDays) {
			val animatorLoveShape = LoveShape(animatorRect)
			animatorLoveShape.drawLoveSolid(canvas)
		}
	}
	
	private fun drawTextInRect(text: String, canvas: Canvas) {
		val rectCenterX = (rect.right - rect.left) / 2 + rect.left
		val rectCenterY = (rect.bottom - rect.top) / 2 + rect.top
		val textWidth = paint.measureText(text)
		val textHeight = getTextHeight()
		canvas.drawText(text, rectCenterX - textWidth / 2, rectCenterY + textHeight / 2 - 5f, paint)
	}
	
	fun setDays(days: List<Int>) {
		daySet.clear()
		daySet.addAll(days)
		invalidate()
	}
	
	private fun getTextHeight(): Float {
		val fontMetrics = paint.fontMetrics
		return fontMetrics.bottom - fontMetrics.top
	}
}