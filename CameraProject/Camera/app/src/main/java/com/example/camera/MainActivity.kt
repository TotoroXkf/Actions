package com.example.camera

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	private lateinit var mCameraFragment: Fragment
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		init()
	}
	
	private fun init() {
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
		mCameraFragment = CameraFragment()
		
		leanCloudInit(applicationContext)
		getServerIpAddress {
			sendDeviceIp(it)
		}
	}
	
	private fun sendDeviceIp(serverIp: String) {
		Thread {
			val deviceIp = getDeviceIp(this@MainActivity)
			val deviceNumber = sendIpAndGetDeviceNumber(serverIp, deviceIp)
			showCamera(deviceNumber)
		}.start()
	}
	
	private fun showCamera(deviceNumber: Int) {
		runOnUiThread {
			number.text = deviceNumber.toString()
			
			supportFragmentManager.beginTransaction().replace(R.id.replaceContent, mCameraFragment)
				.commit()
		}
		
	}
}
