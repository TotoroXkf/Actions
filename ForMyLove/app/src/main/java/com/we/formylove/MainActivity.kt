package com.we.formylove

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.we.common.CommonHandler
import com.we.splash.SplashFragment
import com.we.splash.SplashHandler

class MainActivity : AppCompatActivity(), SplashHandler, CommonHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.anim_splash_alpha_enter, R.anim.anim_splash_alpha_exit)
            .replace(R.id.container, SplashFragment())
            .commit()
    }

    /**
     * 设置全屏
     */
    override fun fullScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    /**
     * 隐藏软键盘
     */
    override fun hideKeyboard() {
        val v = findViewById<View>(android.R.id.content)
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (v != null && im != null) {
            im.hideSoftInputFromWindow(
                v.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    /**
     * 弹起软键盘
     */
    override fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.toggleSoftInput(0, InputMethodManager.SHOW_FORCED)
    }

    override fun startMain() {

    }
}
