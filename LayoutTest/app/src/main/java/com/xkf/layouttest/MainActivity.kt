package com.xkf.layouttest

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var loadLayoutAnimation: LayoutAnimationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadLayoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.animation_controller)
        rootLayout.layoutAnimation = loadLayoutAnimation

        button.setOnClickListener {
            val textView = TextView(this)
            textView.text = System.currentTimeMillis().toString()
            rootLayout.addView(textView, 0)
        }
    }
}
