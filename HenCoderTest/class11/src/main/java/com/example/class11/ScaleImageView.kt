package com.example.class11

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
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
    
    private var animationScaleValue = 1f
        set(value) {
            field = value
            invalidate()
        }
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
        
        animator.setFloatValues(1f, scaleValue)
        animator.target = this
        animator.setPropertyName("animationScaleValue")
        animator.duration = 300
    }
    
    override fun onDraw(canvas: Canvas) {
        canvas.save()
        if (isBigMode) {
            canvas.translate(offsetX, offsetY)
        }
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
        
        offsetX -= distanceX
        if (offsetX > maxOffsetX) {
            offsetX = maxOffsetX
        } else if (offsetX < minOffsetX) {
            offsetX = minOffsetX
        }
        
        offsetY -= distanceY
        if (offsetY > maxOffsetY) {
            offsetY = maxOffsetY
        } else if (offsetY < minOffsetY) {
            offsetY = minOffsetY
        }
        
        invalidate()
        return true
    }
    
    override fun onLongPress(e: MotionEvent?) {
    
    }
    
    override fun onDoubleTap(e: MotionEvent): Boolean {
        isBigMode = !isBigMode
        offsetX = 0f
        offsetY = 0f
        if (isBigMode) {
            animator.start()
        } else {
            animator.reverse()
        }
        return true
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