package com.example.formylove.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Shader
import android.graphics.LinearGradient
import android.util.Log
import android.view.animation.Animation


class SplashBackgroundView :View{
    private val paint = Paint()
    private lateinit var rect:RectF
    private lateinit var shader: LinearGradient
    private val shaderMatrix = Matrix()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        paint.isAntiAlias = true

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        shader = LinearGradient(0f, 0f, w.toFloat(), h.toFloat(), Color.parseColor("#FFF3B0"),
                Color.parseColor("#CA26FF"), Shader.TileMode.CLAMP)
        paint.shader = shader
        rect = RectF(0f,0f,w.toFloat(),h.toFloat())

    }

    fun startAnimation(){
        post {
            val animation = ObjectAnimator.ofFloat(0f,365f)
            animation.duration = 1000*60
            animation.repeatCount = Animation.INFINITE
            animation.addUpdateListener {valueAnimator ->
                val value = valueAnimator.animatedValue as Float
                shaderMatrix.reset()
                shaderMatrix.setRotate(value, (measuredWidth/2).toFloat(), (measuredHeight/2).toFloat())
                shader.setLocalMatrix(shaderMatrix)
                invalidate()
            }
            animation.start()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?:return
        canvas.drawRect(rect,paint)
    }
}