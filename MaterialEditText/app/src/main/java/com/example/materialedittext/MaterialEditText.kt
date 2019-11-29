package com.example.materialedittext

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText

class MaterialEditText(context: Context?, attrs: AttributeSet?) : EditText(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var defaultPaddingValue = 0
    var useFloatingLabel = true
        set(value) {
            field = value
            onUseLabelStateChange()
            requestLayout()
        }
    private var hasTextBefore = false
    
    private var labelPosition = 0f
        set(value) {
            field = value
            invalidate()
        }
    private val labelPositionProperty = PropertyValuesHolder.ofFloat(
        "labelPosition",
        20.toPx() + 20.toPx(),
        20.toPx()
    )
    
    private var labelAlpha = 0
        set(value) {
            field = value
            invalidate()
        }
    private val labelAlphaProperty = PropertyValuesHolder.ofInt(
        "labelAlpha",
        0,
        255
    )
    
    private val animator = ObjectAnimator.ofPropertyValuesHolder(
        this,
        labelPositionProperty,
        labelAlphaProperty
    )
    
    init {
        init(context!!, attrs!!)
    }
    
    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useLabel, true)
        typedArray.recycle()
        
        paint.textSize = 25f
        paint.color = Color.GRAY
        
        onUseLabelStateChange()
        
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            
            }
            
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            
            }
            
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (hasTextBefore && s.isEmpty()) {
                    hasTextBefore = false
                    animator.reverse()
                } else if (!hasTextBefore && s.isNotEmpty()) {
                    hasTextBefore = true
                    animator.start()
                }
            }
        })
    }
    
    private fun onUseLabelStateChange() {
        val paddingTopValue: Int =
            if (useFloatingLabel) {
                defaultPaddingValue + 30.toPx().toInt()
            } else {
                defaultPaddingValue
            }
        setPadding(paddingLeft, paddingTopValue, paddingRight, paddingBottom)
    }
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        if (useFloatingLabel) {
            paint.alpha = labelAlpha
            canvas.drawText(hint.toString(), 10f, labelPosition, paint)
        }
    }
}