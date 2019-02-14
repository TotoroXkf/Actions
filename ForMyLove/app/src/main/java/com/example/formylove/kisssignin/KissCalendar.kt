package com.example.formylove.kisssignin

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

class KissCalendar : View {
	private val rect = RectF(0f, 0f, 0f, 0f)
	private var rectLength = 0f
	
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	
	init {
		val dm = resources.displayMetrics
		val screenWidth = dm.widthPixels
		rectLength = screenWidth.toFloat() / 7f
	}
}