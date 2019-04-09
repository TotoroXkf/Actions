package com.example.client.main

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.client.R

class MainActivity : AppCompatActivity() {
	private val handler = Handler()
	private var model: MainModel? = null
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
		viewModel?.liveData?.observe(this, Observer<MainModel> {
			this@MainActivity.model = it
			view?.update(it)
		})
		
		handler.post(checkPermissionRunnable)
	}
	
	private val checkPermissionRunnable = object : Runnable {
		override fun run() {
			if (checkPermissions()) {
				handler.removeCallbacks(this)
				
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
