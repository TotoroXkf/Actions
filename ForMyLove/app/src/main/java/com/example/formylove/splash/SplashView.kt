package com.example.formylove.splash

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SplashView:FrameLayout{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        val splashBackgroundView = SplashBackgroundView(context)
        val splashBackgroundViewLp = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        splashBackgroundView.layoutParams = splashBackgroundViewLp
        addView(splashBackgroundView)
        splashBackgroundView.startAnimation()
    }
}