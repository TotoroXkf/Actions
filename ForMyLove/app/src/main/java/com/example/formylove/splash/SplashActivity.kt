package com.example.formylove.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.example.formylove.R
import com.example.formylove.utils.fullScreen
import site.gemus.openingstartanimation.OpeningStartAnimation
import site.gemus.openingstartanimation.RedYellowBlueDrawStrategy


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fullScreen(window)
        val openingStartAnimation = OpeningStartAnimation.Builder(this)
            .setDrawStategy(RedYellowBlueDrawStrategy())
            .setAppStatement("大喵和小可爱的故事")
            .setAnimationInterval(1500)
            .create()
        openingStartAnimation.show(this)
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 2000)
    }
}
