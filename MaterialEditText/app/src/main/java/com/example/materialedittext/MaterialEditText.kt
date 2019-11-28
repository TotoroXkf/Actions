package com.example.materialedittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.EditText

class MaterialEditText : EditText {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var defaultPaddingValue = 0
    var useFloatingLabel = true
    
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    
    private fun init() {
        onUseLabelStateChange()
    }
    
    private fun onUseLabelStateChange() {
        val paddingTopValue: Int =
            if (useFloatingLabel) {
                defaultPaddingValue + 40.toPx().toInt()
            } else {
                defaultPaddingValue
            }
        setPadding(paddingLeft, paddingTopValue, paddingRight, paddingBottom)
    }
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}