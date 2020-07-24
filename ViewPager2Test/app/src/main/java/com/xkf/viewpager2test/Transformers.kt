package com.xkf.viewpager2test

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ScaleInTransformer : ViewPager2.PageTransformer {
    /**
     * 主要的实现方法
     * position是当前页的相对位置。0表示当前正中间的一页，1.0表示右侧的第一页，-1.0代表左侧的第一页
     * view是position所对应那一页的view
     */
    override fun transformPage(view: View, position: Float) {
        val minScale = 0.85f
        val center = 0.5f
        // 这里假设屏幕中最多有3个item
        // 屏幕之外的item不处理
        if (position > 1 || position < -1) {
            view.scaleX = minScale
            view.scaleY = minScale
            view.pivotX = view.width / 2f
            view.pivotY = view.height / 2f
            return
        }
        view.pivotY = view.height / 2f
        // 从-1到0表示左侧的item
        if (position >= -1 && position < 0) {
            // position = -1时 , scale = 0.85 , pivot = 100%
            // position = -0.5时 , scale = 0.925 , pivot = 75%
            // position = -025时 , scale = 0.9625 , pivot = 87.5%
            val scaleValue = minScale + (1 + position) * (1 - minScale)
            view.scaleX = scaleValue
            view.scaleY = scaleValue
            view.pivotX = view.width * (center + center * -position)
        } else { // 反之则是右侧的item
            // position = 1时 , scale = 0.85 , pivot = 0%
            // position = 0.5时 , scale = 0.925 , pivot = 25%
            // position = 0时 , scale = 1 , pivot = 50%
            val scaleValue = minScale + (1 - position) * (1 - minScale)
            view.scaleX = scaleValue
            view.scaleY = scaleValue
            view.pivotX = view.width * (center - center * position)
        }
    }
}