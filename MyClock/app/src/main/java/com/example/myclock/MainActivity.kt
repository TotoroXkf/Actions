package com.example.myclock

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		//隐藏状态栏
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
		//设置屏幕亮
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
	}
}
