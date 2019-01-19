package com.example.camera

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.otaliastudios.cameraview.CameraView
import kotlinx.android.synthetic.main.activity_main.*

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
	
	fun takePicture() {
	
	}
}