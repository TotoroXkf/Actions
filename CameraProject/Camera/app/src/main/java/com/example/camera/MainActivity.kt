package com.example.camera

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.otaliastudios.cameraview.CameraListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
		
		//todo 解决socket通信的问题
	}
	
	override fun onResume() {
		super.onResume()
		cameraView.start()
	}
	
	override fun onPause() {
		super.onPause()
		cameraView.stop()
	}
	
	override fun onDestroy() {
		super.onDestroy()
		cameraView.destroy()
	}
}
