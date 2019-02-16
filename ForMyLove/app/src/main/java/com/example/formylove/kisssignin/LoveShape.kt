package com.example.formylove.kisssignin

import android.graphics.Canvas
import android.graphics.RectF
import kotlin.math.sqrt

class LoveShape(private val rect: RectF) {
	private val rate = 4 * (sqrt(2f) - 1) / 3
	private val radius = (rect.right - rect.left) / 2
	
	private var topPointX = 0f
	private var topPointY = 0f
	private var leftPointX = 0f
	private var leftPointY = 0f
	private var rightPointX = 0f
	private var rightPointY = 0f
	private var bottomPointX = 0f
	private var bottomPointY = 0f
	
	private var leftTopPointX1 = 0f
	private var leftTopPointX2 = 0f
	private var leftTopPointY1 = 0f
	private var leftTopPointY2 = 0f
	private var rightTopPointX1 = 0f
	private var rightTopPointX2 = 0f
	private var rightTopPointY1 = 0f
	private var rightTopPointY2 = 0f
	private var bottomRightPointX1 = 0f
	private var bottomRightPointX2 = 0f
	private var bottomRightPointY1 = 0f
	private var bottomRightPointY2 = 0f
	private var bottomLeftPointX1 = 0f
	private var bottomLeftPointX2 = 0f
	private var bottomLeftPointY1 = 0f
	private var bottomLeftPointY2 = 0f
	
	init {
		setUpPoints()
	}
	
	private fun setUpPoints() {
		leftPointX = (rect.right + rect.left) / 2
	}
	
	fun setRect(rectF: RectF) {
		rect.set(rectF)
	}
	
	fun drawLove(canvas: Canvas) {
	
	}
}