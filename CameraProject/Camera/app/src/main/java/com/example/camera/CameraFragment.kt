package com.example.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.hardware.camera2.*
import android.media.ImageReader
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import java.util.*
import kotlin.collections.HashMap

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
		init()
		return view
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
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
	
	private val mOnImageAvailableListener = { reader: ImageReader ->
		//处理拍摄照片
		
	}
	
	private fun initCameraAndPreview() {
		mHandlerThread = HandlerThread("Camera2")
		mHandlerThread.start()
		mHandler = Handler(mHandlerThread.looper)
		try {
			val cameraId = "" + CameraCharacteristics.LENS_FACING_FRONT
			mCharacteristics = mCameraManager.getCameraCharacteristics(cameraId)
			
			mImageReader = ImageReader.newInstance(
				mSurfaceView.width,
				mSurfaceView.height,
				ImageFormat.JPEG,
				3
			)
			mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mHandler)
			
			val deviceStateCallback = object : CameraDevice.StateCallback() {
				override fun onOpened(camera: CameraDevice) {
					mCameraDevice = camera
					Log.e("xkf123456789", "createCameraCaptureSession()")
					createCameraCaptureSession()
				}
				
				override fun onDisconnected(camera: CameraDevice) {
				}
				
				override fun onError(camera: CameraDevice, error: Int) {
				}
				
			}
			
			if (ActivityCompat.checkSelfPermission(
					activity!!,
					Manifest.permission.CAMERA
				) != PackageManager.PERMISSION_GRANTED
			) {
				return
			}
			
			mCameraManager.openCamera(cameraId, deviceStateCallback, mHandler)
		} catch (e: CameraAccessException) {
			Log.e("xkf123456789", "${e.message}")
		}
	}
	
	private fun createCameraCaptureSession() {
		val sessionPreviewStateCallback = object : CameraCaptureSession.StateCallback() {
			override fun onConfigureFailed(session: CameraCaptureSession) {
			
			}
			
			override fun onConfigured(session: CameraCaptureSession) {
				mSession = session
				setLocalParameter()
				showPreView(getParameterMap())
			}
			
		}
		
		mCameraDevice.createCaptureSession(
			Arrays.asList(mSurfaceHolder.surface, mImageReader.surface),
			sessionPreviewStateCallback, mHandler
		)
	}
	
	private val mSessionCaptureCallback = object : CameraCaptureSession.CaptureCallback() {
		override fun onCaptureCompleted(
			session: CameraCaptureSession,
			request: CaptureRequest,
			result: TotalCaptureResult
		) {
		
		}
	}
	
	private fun showPreView(parameterMap: HashMap<CaptureRequest.Key<Int>, Int>) {
		mState = STATE_PREVIEW
		try {
			val builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
			builder.addTarget(mSurfaceHolder.surface)
			for ((key, value) in parameterMap) {
				builder.set(key, value)
			}
			mSession.setRepeatingRequest(builder.build(), mSessionCaptureCallback, mHandler)
		} catch (e: CameraAccessException) {
			e.printStackTrace()
		}
	}
	
	private fun takePicture() {
		mState = STATE_CAPTURE
		try {
			val builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
			builder.addTarget(mImageReader.surface)
			
			mSession.stopRepeating()
			mSession.abortCaptures()
			mSession.capture(builder.build(), mSessionCaptureCallback, null)
		} catch (e: CameraAccessException) {
			e.printStackTrace()
		}
	}
}