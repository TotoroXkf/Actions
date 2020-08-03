package com.xkf.viewflipertest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewFlipper: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewFlipper = findViewById(R.id.viewFlipper)
        viewFlipper.inAnimation = AnimationUtils.loadAnimation(this, R.anim.right_in)
        viewFlipper.outAnimation = AnimationUtils.loadAnimation(this, R.anim.right_out)
        for (i in 0 until 5) {
            val itemView = LayoutInflater.from(this).inflate(R.layout.item_view, viewFlipper, false)
            val textView: TextView = itemView.findViewById(R.id.textView)
            textView.text = i.toString()
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            viewFlipper.addView(itemView, layoutParams)
        }
        viewFlipper.post {
            viewFlipper.startFlipping()
        }
    }
}