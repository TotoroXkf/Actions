package com.xkf.trainingplatform

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.ActivitySplashBinding
import com.xkf.trainingplatform.main.MainActivity
import com.xkf.trainingplatform.register.RegisterActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        viewBinding.root.postDelayed({
            if (Global.isLogin()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, RegisterActivity::class.java))
            }

            finish()
        }, 800)
    }
}