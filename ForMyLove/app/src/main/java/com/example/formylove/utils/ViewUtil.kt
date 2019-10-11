package com.example.formylove.utils

import android.view.Window
import android.view.WindowManager

/**
 * @Author: xiakaifa
 * @Create: 2019-10-11
 * @Description:
 *
 */

fun fullScreen(window: Window) {
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}