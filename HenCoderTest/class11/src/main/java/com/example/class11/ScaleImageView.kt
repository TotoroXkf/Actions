package com.example.class11

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller

class ScaleImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {
    
    private val imageWidth = getDp(300f).toInt()
    private lateinit var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    private val gestureDetector: GestureDetector = GestureDetector(context, this)
    
    private var isBigMode = false
    private var scaleValue = 1f
    private var scaleRatio = 2f
    
    private var offsetX = 0f
    private var offsetY = 0f
    private var maxOffsetX = 0f
    private var minOffsetX = 0f
    private var maxOffsetY = 0f
    private var minOffsetY = 0f
    private val offsetXValueHolder = PropertyValuesHolder.ofFloat("offsetX", 0f)
    private val offsetYValueHolder = PropertyValuesHolder.ofFloat("offsetY", 0f)
    
    private var animationScaleValue = 1f
        set(value) {
            field = value
            invalidate()
        }
    private val scaleValueHolder = PropertyValuesHolder.ofFloat("animationScaleValue", 0f)
    
    private val animator = ObjectAnimator()
    
    private val scroller = OverScroller(context)
    
    init {
        gestureDetector.setOnDoubleTapListener(this)
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        
        bitmap = getBitmap(resources, R.drawable.image, imageWidth)
        scaleValue = w.toFloat() / bitmap.width.toFloat() * scaleRatio
        
        maxOffsetX = (bitmap.width * scaleValue - width) / 2
        minOffsetX = -maxOffsetX
        maxOffsetY = (bitmap.height * scaleValue - height) / 2
        minOffsetY = -maxOffsetY
        
        scaleValueHolder.setFloatValues(1f, scaleValue)
        animator.target = this
        animator.setPropertyName("animationScaleValue")
        animator.duration = 300
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate(offsetX, offsetY)
        canvas.scale(animationScaleValue, animationScaleValue, width / 2f, height / 2f)
        val left = (width.toFloat() - bitmap.width.toFloat()) / 2f
        val top = (height.toFloat() - bitmap.height.toFloat()) / 2f
        canvas.drawBitmap(bitmap, left, top, paint)
        canvas.restore()
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
    
    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }
    
    override fun onShowPress(e: MotionEvent?) {
    
    }
    
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }
    
    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (isBigMode) {
            scroller.fling(
                offsetX.toInt(),
                offsetY.toInt(),
                velocityX.toInt(),
                velocityY.toInt(),
                minOffsetX.toInt(),
                maxOffsetX.toInt(),
                minOffsetY.toInt(),
                minOffsetY.toInt(),
                100,
                100
            )
            postOnAnimation(this)
        }
        
        return true
    }
    
    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (!isBigMode) {
            return true
        }
        
        rangeOffsetX(offsetX - distanceX)
        rangeOffsetY(offsetY - distanceY)
        
        invalidate()
        return true
    }
    
    override fun onLongPress(e: MotionEvent?) {
    
    }
    
    override fun onDoubleTap(e: MotionEvent): Boolean {
        isBigMode = !isBigMode
        if (isBigMode) {
            rangeOffsetX((e.x - width / 2) - ((e.x - width / 2) * scaleValue))
            rangeOffsetY((e.y - height / 2) - ((e.y - height / 2) * scaleValue))
        }
        offsetXValueHolder.setFloatValues(0f, offsetX)
        offsetYValueHolder.setFloatValues(0f, offsetY)
        if (isBigMode) {
            startAnimation()
        } else {
            reverseAnimation()
        }
        return true
    }
    
    private fun rangeOffsetX(x: Float) {
        offsetX = x
        if (offsetX > maxOffsetX) {
            offsetX = maxOffsetX
        } else if (offsetX < minOffsetX) {
            offsetX = minOffsetX
        }
    }
    
    private fun rangeOffsetY(y: Float) {
        offsetY = y
        if (offsetY > maxOffsetY) {
            offsetY = maxOffsetY
        } else if (offsetY < minOffsetY) {
            offsetY = minOffsetY
        }
    }
    
    fun setOffsetX(x: Float) {
        offsetX = x
        invalidate()
    }
    
    fun setOffsetY(y: Float) {
        offsetY = y
        invalidate()
    }
    
    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }
    
    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }
    
    override fun run() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
            postOnAnimation(this)
        }
    }
    
    private fun startAnimation() {
        animator.setValues(scaleValueHolder, offsetXValueHolder, offsetYValueHolder)
        animator.start()
    }
    
    private fun reverseAnimation() {
        animator.setValues(scaleValueHolder, offsetXValueHolder, offsetYValueHolder)
        animator.reverse()
    }
}

fun getBitmap(resources: Resources, id: Int, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, id, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, id, options)
}

fun getDp(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    )
}