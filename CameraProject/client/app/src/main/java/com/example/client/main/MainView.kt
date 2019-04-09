package com.example.client.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.client.R
import com.otaliastudios.cameraview.CameraView
import kotlinx.android.synthetic.main.activity_main.view.*

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
	}
	
	fun update(model: MainModel) {
		progressBar?.visibility = getShowState(model.isLoading)
		textNumber?.visibility = getShowState(model.showNumber)
		cameraView?.visibility = getShowState(model.showCamera)
		
	}
	
	private fun getShowState(need: Boolean): Int {
		return if (need) {
			View.VISIBLE
		} else {
			View.GONE
		}
	}
}


