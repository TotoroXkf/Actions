package com.example.formylove.utils

import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @Author: xiakaifa
 * @Create: 2019-10-11
 * @Description:
 *
 */

fun fullScreen(window: Window) {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}

fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun px2Dp(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun ImageView.loadNetWorkImage(url: String) {
    Glide.with(this).load(url).into(this)
}