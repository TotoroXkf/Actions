package com.example.androidtest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.IOException

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		val client = OkHttpClient()
		client.newCall(Request.Builder().get().url("xxx").build()).enqueue(object :okhttp3.Callback{
			override fun onFailure(call: okhttp3.Call, e: IOException) {
			
			}
			
			override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
			
			}
		})
	}
}
