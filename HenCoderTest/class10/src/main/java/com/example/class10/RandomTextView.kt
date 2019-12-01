package com.example.class10

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import kotlin.math.floor

class RandomTextView(context: Context?, attrs: AttributeSet?) : TextView(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    private val colors = arrayOf(
        Color.BLUE,
        Color.RED,
        Color.GREEN,
        Color.YELLOW,
        Color.DKGRAY,
        Color.MAGENTA
    )
    
    private val textSizes = arrayOf(
        16f, 22f, 27f
    )
    
    private val colorIndex: Int
    
    init {
        setTextColor(Color.WHITE)
        text = "12345"
        
        val textSizeIndex = floor(textSizes.size * Math.random()).toInt()
        setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizes[textSizeIndex])
        
        colorIndex = floor(colors.size * Math.random()).toInt()
        paint.color = colors[colorIndex]
        
        setPadding(25, 15, 25, 15)
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            20f,
            20f,
            paint
        )
        super.onDraw(canvas)
    }
}