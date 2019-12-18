package com.example.formylove.utils

import android.content.res.Resources
import android.util.TypedValue
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

fun dp2px(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)

fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels

fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

fun ImageView.loadNetWorkImage(url: String) {
    Glide.with(this).load(url).into(this)
}