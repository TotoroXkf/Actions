package com.we.formylove

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.we.splash.SplashFragment
import com.we.splash.SplashHandler

class MainActivity : AppCompatActivity(), SplashHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container, SplashFragment()).commit()
    }

    override fun test() {

    }
}
