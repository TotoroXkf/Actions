package formylove.turntable

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.example.formylove.R
import formylove.utils.getBitmap
import java.lang.Integer.min

class TurntableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var radius: Float = 0f
    private val rectF = RectF()
    private val paint = Paint()
    private var colorList = mutableListOf<Int>()
    private lateinit var bitmap: Bitmap

    private val animator = ObjectAnimator()
    private var rotateAngle = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL

        animator.setPropertyName("rotateAngle")
        animator.target = this
        animator.interpolator = DecelerateInterpolator()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val size = min(w, h)
        radius = size / 4f
        rectF.set(w / 2 - radius, h / 2 - radius, w / 2 + radius, h / 2 + radius)

        bitmap = getBitmap(resources, R.drawable.img_arrow_up, 100)
        val ratio = bitmap.width.toFloat() / bitmap.height.toFloat()
        val dstHeight = radius * 0.8f
        val dstWidth = ratio * dstHeight
        bitmap = Bitmap.createScaledBitmap(bitmap, dstWidth.toInt(), dstHeight.toInt(), false)
    }

    fun addAnimationEndListener(callback: () -> Unit) {
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                callback.invoke()
            }
        })
    }

    fun resetRotate() {
        rotateAngle = 0f
        invalidate()
    }

    fun rotate(angle: Float) {
        if (angle < 0 || animator.isRunning) {
            return
        }

        animator.duration = (((angle / 360) + 1) * 1000).toLong()
        animator.setFloatValues(0f, angle)
        animator.start()
    }

    fun setColorList(newColorList: MutableList<Int>) {
        if (animator.isRunning) {
            return
        }
        rotateAngle = 0f
        colorList = newColorList
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val angle = 360 / colorList.size.toFloat()
        var currentAngle = -90f
        canvas.save()
        canvas.rotate(rotateAngle, width / 2f, height / 2f)
        for (color in colorList) {
            paint.color = color
            canvas.drawArc(rectF, currentAngle, angle, true, paint)
            currentAngle += angle
        }
        canvas.restore()
        if (colorList.isEmpty()) {
            return
        }
        val offsetX = width / 2f - bitmap.width / 2f
        val offsetY = height / 2f - bitmap.height.toFloat()
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }
}