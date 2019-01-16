package com.example.camera

import android.content.Context
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.media.ImageReader
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v4.app.Fragment
import android.view.*

class CameraFragment : Fragment() {
	private val STATE_PREVIEW = 0
	private val STATE_CAPTURE = 1
	private val TAG = "xkf123456"
	
	private lateinit var mSurfaceView: SurfaceView
	private lateinit var mSurfaceHolder: SurfaceHolder
	
	private lateinit var mCameraManager: CameraManager
	private lateinit var mCameraDevice: CameraDevice
	private lateinit var mHandler: Handler
	private lateinit var mImageReader: ImageReader
	private var mState: Int = 0
	private lateinit var mSession: CameraCaptureSession
	private lateinit var mHandlerThread: HandlerThread
	private lateinit var mCharacteristics: CameraCharacteristics
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_camera, container, false)
		mSurfaceView = view.findViewById(R.id.surfaceView)
		return view
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		init()
	}
	
	private fun init() {
		mCameraManager = activity!!.getSystemService(Context.CAMERA_SERVICE) as CameraManager
		mSurfaceHolder = mSurfaceView.holder
		mSurfaceHolder.addCallback(object : SurfaceHolder.Callback {
			override fun surfaceChanged(
				holder: SurfaceHolder?,
				format: Int,
				width: Int,
				height: Int
			) {
			
			}
			
			override fun surfaceDestroyed(holder: SurfaceHolder?) {
			}
			
			override fun surfaceCreated(holder: SurfaceHolder?) {
				initCameraAndPreview()
			}
		})
	}
	
	private fun initCameraAndPreview() {
		
	}
	
	
}