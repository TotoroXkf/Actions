package com.example.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

//todo 创建相机的Fragment
//todo 使用socket通信
//todo 命令沟通
//todo 参数设置

class MainActivity : AppCompatActivity() {
	val permissions = arrayListOf(Manifest.permission.CAMERA)
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			checkPermissions()
		} else {
			showPreview()
		}
	}
	
	@RequiresApi(Build.VERSION_CODES.M)
	private fun checkPermissions() {
		val needPermissions = ArrayList<String>()
		for (permission in permissions) {
			if (ContextCompat.checkSelfPermission(
					this,
					permission
				) != PackageManager.PERMISSION_GRANTED
			) {
				needPermissions.add(permission)
			}
		}
		if (needPermissions.isNotEmpty()) {
			requestPermissions(needPermissions.toTypedArray(), 1)
		} else {
			showPreview()
		}
	}
	
	private fun showPreview() {
		supportFragmentManager.beginTransaction().replace(R.id.replaceContent, CameraFragment())
			.commit()
	}
	
	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		for (i in grantResults) {
			if (i != PackageManager.PERMISSION_GRANTED) {
				Toast.makeText(this, "缺少权限", Toast.LENGTH_SHORT).show()
				System.exit(0)
			}
		}
		showPreview()
	}
}
