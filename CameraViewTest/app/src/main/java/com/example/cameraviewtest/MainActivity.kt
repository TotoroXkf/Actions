package com.example.cameraviewtest

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
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
//					camera.visibility = View.GONE
//					val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
//					imageView.visibility = View.VISIBLE
//					imageView.setImageBitmap(bitmap)
				}
			}
		})
		button.setOnClickListener {
			camera.takePicture()
		}
		
//		ActivityCompat.requestPermissions(
//			this, arrayOf(Manifest.permission.CAMERA)
//			, 1
//		)
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
