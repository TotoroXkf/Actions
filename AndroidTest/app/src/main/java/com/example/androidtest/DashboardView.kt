package com.example.androidtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private val paint: Paint = Paint()
	
	init {
		paint.isAntiAlias = true
	}
	
	override fun onDraw(canvas: Canvas) {
		
	}
}