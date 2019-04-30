package com.example.client.main

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.client.R
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import org.greenrobot.eventbus.EventBus

class MainView : FrameLayout {
	var cameraView: CameraView? = null
	var textNumber: TextView? = null
	var progressBar: ProgressBar? = null
	
	constructor(context: Context) : super(context)
	constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
		context,
		attrs,
		defStyleAttr
	)
	
	override fun onFinishInflate() {
		super.onFinishInflate()
		cameraView = findViewById(R.id.camera_view)
		textNumber = findViewById(R.id.text_number)
		progressBar = findViewById(R.id.progress_bar)
		cameraView?.run {
			setLifecycleOwner(context as MainActivity)
			addCameraListener(object : CameraListener() {
				override fun onPictureTaken(jpeg: ByteArray?) {
					if (jpeg == null) {
						return
					}
					EventBus.getDefault().post(jpeg)
				}
			})
		}
	}
	
	fun setState(viewState: MainViewState) {
		progressBar?.visibility = getShowState(viewState.isLoading)
		textNumber?.visibility = getShowState(viewState.showNumber)
		textNumber?.text = viewState.number
		cameraView?.visibility = getShowState(viewState.showCamera)
	}
	
	private fun getShowState(need: Boolean): Int {
		return if (need) {
			View.VISIBLE
		} else {
			View.GONE
		}
	}
	
	fun setCameraViewParameter(parameter: CameraParameter) {
		cameraView?.run {
			flash = parameter.flash
			whiteBalance = parameter.whiteBalance
			hdr = parameter.hdr
			audio = parameter.audio
			for ((key, value) in parameter.gestures.entries) {
				mapGesture(key, value)
			}
			playSounds = parameter.playSound
			grid = parameter.gridLine
//			setGridColor(parameter.gridLineColor)
//			setCameraAutoFocusResetDelay(1000)
		}
	}
}


