package com.example.formylove.kisssignin

import android.graphics.*
import android.util.Log
import kotlin.math.sqrt

class LoveShape(private val rect: RectF) {
	private val rate = 4 * (sqrt(2f) - 1) / 3
	private val radius = (rect.right - rect.left) / 2
	
	private val points = FloatArray(8)
	private val controlPoints = FloatArray(16)
	private val path = Path()
	private val paint = Paint()
	
	init {
		paint.isAntiAlias = true
		paint.color = Color.RED
		setUpPoints()
	}
	
	private fun setUpPoints() {
		//上面的点
		points[0] = (rect.left + rect.right) / 2
		points[1] = rect.top
		//右边的点
		points[2] = rect.right
		points[3] = (rect.top + rect.bottom) / 2
		//底下的点
		points[4] = (rect.left + rect.right) / 2
		points[5] = rect.bottom
		//左边的点
		points[6] = rect.left
		points[7] = (rect.top + rect.bottom) / 2
		
		//左上的第一个控制点
		controlPoints[0] = points[6]
		controlPoints[1] = points[7] - radius * rate
		//左上的第二个控制点
		controlPoints[2] = points[0] - radius * rate
		controlPoints[3] = points[1]
		//右上的第一个控制点
		controlPoints[4] = points[0] + radius * rate
		controlPoints[5] = points[1]
		//右上的第二个控制点
		controlPoints[6] = points[2]
		controlPoints[7] = points[3] - radius * rate
		//右下的第一个控制点
		controlPoints[8] = points[2]
		controlPoints[9] = points[3] + radius * rate
		//右下的第二个控制点
		controlPoints[10] = points[4] + radius * rate
		controlPoints[11] = points[5]
		//左下的第一个控制点
		controlPoints[12] = points[4] - radius * rate
		controlPoints[13] = points[5]
		//左下的第二个控制点
		controlPoints[14] = points[6]
		controlPoints[15] = points[7] + radius * rate
	}
	
	fun setRect(rectF: RectF) {
		rect.set(rectF)
	}
	
	fun drawLove(canvas: Canvas) {
		path.reset()
		path.moveTo(points[6], points[7])
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