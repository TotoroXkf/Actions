package com.example.longimagetest

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class LongImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private lateinit var bitmap: Bitmap
	private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
	private val bitmapRect = Rect()
	private val canvasRect = Rect()
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		
		bitmap = getBitmap()
		canvasRect.set(0, 0, w, h)
		bitmapRect.set(0, 0, bitmap.width, height)
	}
	
	override fun onDraw(canvas: Canvas) {
		// 第一个Rect 代表要绘制的 bitmap 区域
		// 第二个 Rect 代表的是要将 bitmap 绘制在屏幕的什么地方
		canvas.drawBitmap(bitmap, bitmapRect, canvasRect, paint)
	}
	
	private fun getBitmap(): Bitmap {
		val options = BitmapFactory.Options()
		options.inJustDecodeBounds = true
		BitmapFactory.decodeResource(resources, R.drawable.image, options)
		options.inJustDecodeBounds = false
		options.inDensity = options.outWidth
		options.inTargetDensity = Resources.getSystem().displayMetrics.widthPixels
		return BitmapFactory.decodeResource(resources, R.drawable.image, options)
	}
}