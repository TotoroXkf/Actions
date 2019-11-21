package com.example.androidtest

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CircleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private val paint = Paint()
	private lateinit var bitmap: Bitmap
	private var bitmapWidth = dp2px(200f)
	private var rectf = RectF()
	val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
	
	init {
		paint.isAntiAlias = true
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		
		bitmap = getBitmap()
		rectf.set(
			dp2px(30f),
			dp2px(30f),
			dp2px(30f) + bitmapWidth,
			dp2px(30f) + bitmapWidth
		)
	}
	
	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		canvas.drawCircle(
			bitmapWidth / 2 + dp2px(30f),
			bitmapWidth / 2 + dp2px(30f),
			bitmapWidth / 2 + dp2px(10f),
			paint
		)
		val saveId = canvas.saveLayer(rectf, paint)
		canvas.drawCircle(
			bitmapWidth / 2 + dp2px(30f),
			bitmapWidth / 2 + dp2px(30f),
			bitmapWidth / 2,
			paint
		)
		paint.xfermode = xfermode
		canvas.drawBitmap(bitmap, dp2px(30f), dp2px(30f), paint)
		paint.xfermode = null
		canvas.restoreToCount(saveId)
	}
	
	private fun getBitmap(): Bitmap {
		val options = BitmapFactory.Options()
		options.inJustDecodeBounds = true
		BitmapFactory.decodeResource(resources, R.drawable.test_image, options)
		options.inJustDecodeBounds = false
		options.inDensity = options.outWidth
		options.inTargetDensity = bitmapWidth.toInt()
		return BitmapFactory.decodeResource(resources, R.drawable.test_image, options)
	}
}