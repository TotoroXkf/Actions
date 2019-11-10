package com.example.formylove.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formylove.R
import com.example.formylove.main.MainActivity
import com.example.formylove.utils.fullScreen
import kotlinx.android.synthetic.main.activity_splash.*


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
