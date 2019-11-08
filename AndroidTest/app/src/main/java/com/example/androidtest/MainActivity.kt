package com.example.androidtest

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.RemoteViews
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = Button(this)
        button.text = "button"
        val lp = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            0,
            0,
            PixelFormat.TRANSPARENT
        )
        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        lp.gravity = Gravity.START or Gravity.TOP
        lp.x = 100;
        lp.y = 300;
        windowManager.addView(button, lp)
    }
}
