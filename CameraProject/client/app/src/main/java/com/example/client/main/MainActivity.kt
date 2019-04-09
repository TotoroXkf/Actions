package com.example.client.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.client.R

class MainActivity : AppCompatActivity() {
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
		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		viewModel?.liveData?.observe(this, Observer<MainModel> {
			this@MainActivity.model = it
			view?.update(it)
		})
		
	}
	
	fun requestPermission() {
	
	}
}
