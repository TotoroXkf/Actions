package com.example.hooktest

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HookHelper.hook(Activity::class.java, this)

        button.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }
}
