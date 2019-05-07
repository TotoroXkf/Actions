package com.example.cameraviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cameraviewtest.camera.CameraView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		camera.addCallback(object : CameraView.Callback() {
			override fun onCameraOpened(cameraView: CameraView?) {
				super.onCameraOpened(cameraView)
			}
			
			override fun onCameraClosed(cameraView: CameraView?) {
				super.onCameraClosed(cameraView)
			}
			
			override fun onPictureTaken(cameraView: CameraView?, data: ByteArray?) {
				data?.run {
					Log.e("xkf123456789", "" + data.size)
				}
			}
		})
		button.setOnClickListener {
			camera.takePicture()
		}
	}
	
	override fun onResume() {
		super.onResume()
		camera.start()
	}
	
	override fun onPause() {
		camera.stop()
		super.onPause()
	}
}
