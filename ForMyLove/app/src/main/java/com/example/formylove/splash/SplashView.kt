package com.example.formylove.splash

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import com.example.formylove.utils.computeDays

class SplashView:FrameLayout{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        // 渐变背景
        val splashBackgroundView = SplashBackgroundView(context)
        val splashBackgroundViewLp = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
        splashBackgroundView.layoutParams = splashBackgroundViewLp
        addView(splashBackgroundView)
        splashBackgroundView.startAnimation()

        // 显示天数
        val daysTextView = DaysTextView(context)
        daysTextView.day = computeDays()
        val daysTextViewLp = LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        daysTextViewLp.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
        daysTextViewLp.bottomMargin = 75
        daysTextView.layoutParams = daysTextViewLp
        addView(daysTextView)

        // 图标
    }
}