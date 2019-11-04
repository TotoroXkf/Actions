package com.example.formylove.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.example.formylove.R
import com.example.formylove.main.MainActivity
import com.example.formylove.utils.fullScreen
import kotlinx.android.synthetic.main.activity_splash.*
import site.gemus.openingstartanimation.OpeningStartAnimation
import site.gemus.openingstartanimation.RedYellowBlueDrawStrategy


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fullScreen(window)

        splashView.animationFinishListener = {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
