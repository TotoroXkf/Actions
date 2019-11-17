package com.example.formylove.splash

import android.content.Intent
import com.example.formylove.base.BaseActivity
import com.example.formylove.R
import com.example.formylove.main.MainActivity
import com.example.formylove.utils.fullScreen
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_splash
    
    override fun initViews() {
        fullScreen(window)
        
        splashView.animationFinishListener = {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    
    override fun needDoubleBackExit(): Boolean {
        return true
    }
}
