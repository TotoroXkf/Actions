package com.example.formylove.kisssignin

import android.graphics.Canvas
import android.graphics.RectF
import kotlin.math.sqrt

class LoveShape(private val rect: RectF) {
	private val rate = 4 * (sqrt(2f) - 1) / 3
	private val radius = (rect.right - rect.left) / 2
	
	private val points = ArrayList<Float>()
	private val controlPoints = ArrayList<Float>()
	
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
	private var rightBottomPointX1 = 0f
	private var rightBottomPointX2 = 0f
	private var rightBottomPointY1 = 0f
	private var rightBottomPointY2 = 0f
	private var leftBottomPointX1 = 0f
	private var leftBottomPointX2 = 0f
	private var leftBottomPointY1 = 0f
	private var leftBottomPointY2 = 0f
	
	init {
		setUpPoints()
	}
	
	private fun setUpPoints() {
		//左边的点
		leftPointX = rect.left
		leftPointY = (rect.top + rect.bottom) / 2
		//上面的点
		topPointX = (rect.left + rect.right) / 2
		topPointY = rect.top
		//右边的点
		rightPointX = rect.right
		rightPointY = (rect.top + rect.bottom) / 2
		//底下的点
		bottomPointX = (rect.left + rect.right) / 2
		bottomPointY = rect.bottom
		points.run {
			add(leftPointX)
			add(leftPointY)
			add(topPointX)
			add(topPointY)
			add(rightPointX)
			add(rightPointY)
			add(bottomPointX)
			add(bottomPointY)
		}
		
		//左上的第一个控制点
		leftTopPointX1 = leftPointX
		leftTopPointY1 = leftPointY - radius * rate
		//左上的第二个控制点
		leftTopPointX2 = topPointX - radius * rate
		leftTopPointY2 = topPointY
		//右上的第一个控制点
		rightTopPointX1 = topPointX + radius * rate
		rightTopPointY1 = topPointY
		//右上的第二个控制点
		rightTopPointX2 = rightPointX
		rightTopPointY2 = rightPointY - radius * rate
		//右下的第一个控制点
		rightBottomPointX1 = rightPointX
		rightBottomPointY1 = rightPointY + radius * rate
		//右下的第二个控制点
		rightBottomPointX2 = bottomPointX + radius * rate
		rightBottomPointY2 = bottomPointY
		//左下的第一个控制点
		leftBottomPointX1 = bottomPointX - radius * rate
		leftBottomPointY1 = bottomPointY
		//左下的第二个控制点
		leftBottomPointX2 = leftPointX
		leftBottomPointY2 = leftPointY + radius * rate
		controlPoints.run {
			add(leftTopPointX1)
			add(leftTopPointX2)
			add(leftTopPointY1)
			add(leftTopPointY2)
			add(rightTopPointX1)
			add(rightTopPointX2)
			add(rightTopPointY1)
			add(rightTopPointY2)
			add(rightBottomPointX1)
			add(rightBottomPointX2)
			add(rightBottomPointY1)
			add(rightBottomPointY2)
			add(leftBottomPointX1)
			add(leftBottomPointX2)
			add(leftBottomPointY1)
			add(leftBottomPointY2)
		}
	}
	
	fun setRect(rectF: RectF) {
		rect.set(rectF)
	}
	
	fun drawLove(canvas: Canvas) {
	
	}
}