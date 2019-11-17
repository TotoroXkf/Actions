package com.example.formylove.splash

import android.animation.*
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import com.example.formylove.utils.computeDays
import kotlinx.android.synthetic.main.activity_splash.view.*

class SplashView : FrameLayout {
    private lateinit var animatorSet: AnimatorSet
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
                this@SplashView.setOnClickListener {
                    animationFinishListener?.invoke()
                }
                tvGoNext.visibility = View.VISIBLE
            }
        })
        
        val alphaAnimator = ObjectAnimator.ofFloat(tvGoNext, "alpha", 0f, 1f, 0f)
        alphaAnimator.duration = 2400
        alphaAnimator.repeatCount = Animation.INFINITE
        
        animatorSet = AnimatorSet()
        animatorSet.playSequentially(commonAnimator, alphaAnimator)
        animatorSet.start()
    }
    
    override fun onDetachedFromWindow() {
        animatorSet.cancel()
        super.onDetachedFromWindow()
    }
}