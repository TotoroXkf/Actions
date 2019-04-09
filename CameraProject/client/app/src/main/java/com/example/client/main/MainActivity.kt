package com.example.client.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.client.R

class MainActivity : AppCompatActivity() {
	private val handler = Handler(Looper.getMainLooper())
	private var viewModel: MainViewModel? = null
	private var view: MainView? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		view = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false) as MainView?
		setContentView(view)
		init()
	}
	
	private fun init() {
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
		view?.cameraView?.setLifecycleOwner(this)
		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		viewModel?.viewStateLiveData?.observe(this, Observer<MainViewState> { viewState ->
			view?.update(viewState)
		})
		viewModel?.serverIpLiveData?.observe(this, Observer<String> { ip ->
			if (TextUtils.isEmpty(ip)) {
				return@Observer
			}
		})
		viewModel?.viewStateLiveData?.value = viewModel?.createInitViewState()
		
		handler.post(checkPermissionRunnable)
	}
	
	private val checkPermissionRunnable = object : Runnable {
		override fun run() {
			if (checkPermissions()) {
				handler.removeCallbacks(this)
				viewModel?.getServerIp()
			} else {
				handler.postDelayed(this, 1000)
			}
		}
	}
	
	private fun checkPermissions(): Boolean {
		if (viewModel == null) {
			return false
		}
		for (permission in PERMISSIONS) {
			if (ContextCompat.checkSelfPermission(this, permission)
				!= PackageManager.PERMISSION_GRANTED
			) {
				return false
			}
		}
		return true
	}
}
