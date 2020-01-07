package formylove.random

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.lang.Integer.min

class TurntableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var radius: Float = 0f
    private val rectF = RectF()
    private val paint = Paint()
    private val colorList = mutableListOf<Int>()
    
    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }
    
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val size = min(w, h)
        radius = size / 4f
        rectF.set(w / 2 - radius, h / 2 - radius, w / 2 + radius, h / 2 + radius)
    }
    
    fun rotate(num: Int) {
        
    }
    
    fun setColorList(newColorList: List<Int>) {
        colorList.clear()
        colorList.addAll(newColorList)
        invalidate()
    }
    
    override fun onDraw(canvas: Canvas) {
        val angle = 360 / colorList.size.toFloat()
        var currentAngle = -90f
        for (color in colorList) {
            paint.color = color
            canvas.drawArc(rectF, currentAngle, angle, true, paint)
            currentAngle += angle
        }
        if (colorList.isEmpty()) {
            return
        }
        paint.strokeWidth = 12f
        paint.color = Color.BLACK
        canvas.drawLine(width / 2f, height / 2f, width / 2f, height / 2 - radius * 0.8f, paint)
    }
}