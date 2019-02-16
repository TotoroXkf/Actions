package com.example.formylove.kisssignin

import android.graphics.*
import android.util.Log
import kotlin.math.sqrt

class LoveShape(private val rect: RectF) {
	private val rate = 4 * (sqrt(2f) - 1) / 3
	private val radius = (rect.right - rect.left) / 2
	
	private val points = ArrayList<Float>()
	private val controlPoints = ArrayList<Float>()
	private val path = Path()
	private val paint = Paint()
	
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
		paint.isAntiAlias = true
		paint.color = Color.RED
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
			add(topPointX)
			add(topPointY)
			add(rightPointX)
			add(rightPointY)
			add(bottomPointX)
			add(bottomPointY)
			add(leftPointX)
			add(leftPointY)
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
			add(leftTopPointY1)
			add(leftTopPointX2)
			add(leftTopPointY2)
			add(rightTopPointX1)
			add(rightTopPointY1)
			add(rightTopPointX2)
			add(rightTopPointY2)
			add(rightBottomPointX1)
			add(rightBottomPointY1)
			add(rightBottomPointX2)
			add(rightBottomPointY2)
			add(leftBottomPointX1)
			add(leftBottomPointY1)
			add(leftBottomPointX2)
			add(leftBottomPointY2)
		}
	}
	
	fun setRect(rectF: RectF) {
		rect.set(rectF)
	}
	
	fun drawLove(canvas: Canvas) {
		path.reset()
		path.moveTo(leftPointX, leftPointY)
		var i = 0
		var j = 0
		for (k in 0..3) {
			path.cubicTo(controlPoints[j], controlPoints[j + 1], controlPoints[j + 2], controlPoints[j + 3], points[i], points[i + 1])
			i += 2
			j += 4
		}
		canvas.drawPath(path, paint)
	}
}