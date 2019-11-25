package com.example.androidtest

import android.content.Context
import android.graphics.Canvas
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class MultilineView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
	private lateinit var staticLayout: StaticLayout
	private val textPaint = TextPaint()
	private val text =
		"版权归作者所有 ， 任何形式转载请联系作者 。 作者 ： 南瓜派 （ 来自豆瓣 ） 来源 ： https ://www.douban.com/note/742767985/之所以喜欢旅行，是因为旅行中会遇到一个个瞬间。这些瞬间，构成了假想中的“第二人生”。陌生建筑的新鲜，拐角咖啡的静止，迥异生活的奇妙，异文化的未知与神秘，都会被我们贴上很多漂亮标签，像对待珍贵标本和古董一样保存在记忆相薄。而以下的絮叨，就是一些很私人的瞬间。它不能为你赋予任何意义，仅是时间的一条分叉，单纯地展示了另一种可能性——几乎是你永远无法真正步入的他乡。也正是因为无法步入其中，它会显得很特别。"
	private val floatArray = FloatArray(1)
	
	init {
		textPaint.isAntiAlias = true
		textPaint.textSize = 75f
	}
	
	override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
		super.onSizeChanged(w, h, oldw, oldh)
		
		staticLayout = StaticLayout.Builder.obtain(
			text,
			0,
			text.length,
			textPaint,
			w
		).build()
	}
	
	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		
		// 用这个来实现
		staticLayout.draw(canvas)
		
		// 使用breakText来实现
		var index = textPaint.breakText(text, true, width.toFloat(), floatArray)
		canvas.drawText(text, 0, index, 0f, 100f, textPaint)
		val oldIndex = index
		index = textPaint.breakText(
			text,
			index,
			text.length,
			true,
			width.toFloat(),
			floatArray
		)
		canvas.drawText(text, oldIndex, oldIndex + index, 0f, 200f, textPaint)
	}
}