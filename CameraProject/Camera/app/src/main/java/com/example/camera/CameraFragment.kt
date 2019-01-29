package com.example.camera

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import java.io.ByteArrayOutputStream


class CameraFragment : Fragment() {
	lateinit var mCameraView: CameraView
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_camera, container, false)
		mCameraView = view.findViewById(R.id.cameraView)
		return view
	}
	
	override fun onResume() {
		super.onResume()
		mCameraView.start()
	}
	
	override fun onPause() {
		super.onPause()
		mCameraView.stop()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		mCameraView.destroy()
	}
	
	fun takePicture(callback: (ByteArray) -> Unit) {
		mCameraView.clearCameraListeners()
		mCameraView.addCameraListener(object : CameraListener() {
			override fun onPictureTaken(bytes: ByteArray?) {
				if (bytes == null) {
					return
				}
				mCameraView.post {
					val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
					val matrix = Matrix()
					matrix.postRotate(90f)
					val rotateBitmap =
						Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
					val outputStream = ByteArrayOutputStream()
					rotateBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
					val picture = outputStream.toByteArray()
					callback(picture)
				}
				
			}
		})
		
		mCameraView.capturePicture()
	}
}