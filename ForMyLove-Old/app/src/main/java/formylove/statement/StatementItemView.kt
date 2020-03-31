package formylove.statement

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.item_statement_list.view.*

class StatementItemView(
    context: Context?, attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {
    companion object {
        const val STATE_IDLE = 0
        const val STATE_DRAG_FROM_IDLE = 1
        const val STATE_AUTO_SCROLL = 2
        const val STATE_IDLE_SHOW = 3
        const val STATE_DRAG_FROM_IDLE_SHOW = 4
        
        const val MIN_DRAG_DISTANCE = 16f
    }
    
    private var state = STATE_IDLE
    
    private var downX = 0f
    private var dragDistance = 0f
    
    private val scrollAnimator = ObjectAnimator()
    private var autoScrollValue = 0
        set(value) {
            field = value
            scrollTo(autoScrollValue, 0)
            invalidate()
        }
    
    init {
        scrollAnimator.target = this
        scrollAnimator.setPropertyName("autoScrollValue")
    }
    
    override fun onFinishInflate() {
        super.onFinishInflate()
        
        post {
            val imgDeleteHeight = bgDelete.height / 3
            val imgDeleteWidth = bgDelete.width / 3
            val layoutParams = imgDelete.layoutParams
            layoutParams.width = imgDeleteWidth
            layoutParams.height = imgDeleteHeight
            imgDelete.layoutParams = layoutParams
        }
    }
    
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                if (state == STATE_AUTO_SCROLL) {
                    return false
                }
                downX = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val distance = downX - x
                handleState(distance)
            }
            MotionEvent.ACTION_UP -> {
                if (state == STATE_DRAG_FROM_IDLE || state == STATE_DRAG_FROM_IDLE_SHOW) {
                    setState(STATE_AUTO_SCROLL)
                }
            }
        }
        return true
    }
    
    private fun handleState(distance: Float) {
        when (state) {
            STATE_IDLE -> {
                if (distance > MIN_DRAG_DISTANCE) {
                    dragDistance = fixDistance(distance)
                    setState(STATE_DRAG_FROM_IDLE)
                }
            }
            STATE_IDLE_SHOW -> {
                if (distance < 0) {
                    dragDistance = fixDistance(bgDelete.width.toFloat() + distance)
                    setState(STATE_DRAG_FROM_IDLE_SHOW)
                }
            }
            STATE_DRAG_FROM_IDLE -> {
                if (distance > 0) {
                    dragDistance = fixDistance(distance)
                    setState(STATE_DRAG_FROM_IDLE)
                } else {
                    dragDistance = 0f
                    setState(STATE_DRAG_FROM_IDLE)
                }
            }
            STATE_DRAG_FROM_IDLE_SHOW -> {
                if (distance > 0) {
                    dragDistance = bgDelete.width.toFloat()
                    setState(STATE_DRAG_FROM_IDLE_SHOW)
                } else {
                    dragDistance = fixDistance(bgDelete.width.toFloat() + distance)
                    setState(STATE_DRAG_FROM_IDLE_SHOW)
                }
            }
        }
    }
    
    private fun setState(newState: Int) {
        state = newState
        when (state) {
            STATE_DRAG_FROM_IDLE_SHOW,
            STATE_DRAG_FROM_IDLE -> {
                parent.requestDisallowInterceptTouchEvent(true)
                scrollTo(dragDistance.toInt(), 0)
            }
            STATE_AUTO_SCROLL -> {
                dragDistance = 0f
                if (scrollX > bgDelete.width / 2) {
                    scrollAnimator.setIntValues(scrollX, bgDelete.width)
                    scrollAnimator.removeAllListeners()
                    scrollAnimator.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            setState(STATE_IDLE_SHOW)
                        }
                    })
                } else {
                    scrollAnimator.setIntValues(scrollX, 0)
                    scrollAnimator.removeAllListeners()
                    scrollAnimator.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            setState(STATE_IDLE)
                        }
                    })
                }
                scrollAnimator.start()
            }
        }
        invalidate()
    }
    
    private fun fixDistance(distance: Float): Float {
        val maxValue = bgDelete.width.toFloat()
        val minValue = 0f
        return when {
            distance > maxValue -> {
                maxValue
            }
            distance < minValue -> {
                minValue
            }
            else -> {
                distance
            }
        }
    }
}