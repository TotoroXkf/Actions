package com.example.formylove.kisssignin

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

class KissCalendar : View {
	private var viewWidth = 0f
	private var viewHeight = 0f
	
	private val rect = RectF(0f, 0f, 0f, 0f)
	private var rectLength = 0f
	
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	init {
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		viewWidth = w.toFloat()
		viewHeight = w.toFloat()
		rectLength = viewWidth / 7
		rect.set(0f, 0f, rectLength, rectLength)
	}
	
	fun moveRect() {
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
}