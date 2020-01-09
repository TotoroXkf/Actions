package formylove.turntable

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import java.lang.Integer.min

class TurntableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var radius: Float = 0f
    private val rectF = RectF()
    private val paint = Paint()
    private var colorList = mutableListOf<Int>()

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
    }

    fun addAnimationEndListener(callback: () -> Unit) {
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                callback.invoke()
            }
        })
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
        paint.strokeWidth = 12f
        paint.color = Color.BLACK
        canvas.drawLine(width / 2f, height / 2f, width / 2f, height / 2 - radius * 0.8f, paint)
    }
}