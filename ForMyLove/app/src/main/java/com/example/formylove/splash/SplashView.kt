package com.example.formylove.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.formylove.utils.computeDays
import kotlinx.android.synthetic.main.activity_splash.view.*

class SplashView : FrameLayout {
    var animationFinishListener: (() -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()

        daysView.day = computeDays()
        backgroundView.startAnimation()

        handleAnimation()
    }

    private fun handleAnimation() = post {
        val iconLayoutY = iconLayout.y
        val daysViewY = daysView.y
        val distance = 500f

        val commonAnimator = ValueAnimator.ofFloat(0f, 1f)
        commonAnimator.duration = 1000
        commonAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            val moveValue = distance - distance * value

            daysView.alpha = value
            daysView.scaleX = value
            daysView.scaleY = value
            daysView.y = daysViewY + moveValue

            iconLayout.alpha = value
            iconLayout.scaleX = value
            iconLayout.scaleY = value
            iconLayout.y = iconLayoutY + moveValue
        }
        commonAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                animationFinishListener?.invoke()
            }
        })

        commonAnimator.start()
    }
}