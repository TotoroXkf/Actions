package com.example.myview.bezier

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import com.example.myview.MyView

class BezierView : MyView {
	
	val startPoint = Point()
	val endPoint = Point()
	val controlPoiont = Point()
	
	private lateinit var startRect: RectF
	private lateinit var endRect: RectF
	private lateinit var controlRect: RectF
	
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
	
	override fun init(context: Context, attrs: AttributeSet) {
		setLayerType(LAYER_TYPE_SOFTWARE, null)
		
		startRect = RectF(200f, 500f, 250f, 550f)
		startPoint.x = ((startRect.left + startRect.right) / 2).toInt()
		startPoint.y = ((startRect.top + startRect.bottom) / 2).toInt()
		
		controlRect = RectF(500f, 300f, 550f, 350f)
		
		
		endRect = RectF(800f, 500f, 850f, 550f)
		
	}
	
	override fun widthWrapContent(widthSize: Int, heightSize: Int) {
	
	}
	
	override fun heightWrapContent(heightSize: Int, heightSize1: Int) {
	
	}
	
	@SuppressLint("ClickableViewAccessibility")
	
	override fun onTouchEvent(event: MotionEvent?): Boolean {
		super.onTouchEvent(event)
		when (event!!.action) {
			MotionEvent.ACTION_DOWN -> {
			
			}
			
			MotionEvent.ACTION_MOVE -> {
			
			}
			
			MotionEvent.ACTION_UP -> {
			
			}
			
		}
		return true
	}
	
	override fun drawContent(canvas: Canvas) {
		paint.style = Paint.Style.FILL
		canvas.drawRect(startRect, paint)
		canvas.drawRect(controlRect, paint)
		canvas.drawRect(endRect, paint)
	}
}