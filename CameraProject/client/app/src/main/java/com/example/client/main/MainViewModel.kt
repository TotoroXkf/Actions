package com.example.client.main

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.io.Reader
import java.io.Writer
import java.net.Socket

class MainViewModel : ViewModel() {
	val handler = Handler(Looper.getMainLooper())
	val viewStateLiveData = MutableLiveData<MainViewState>()
	val serverIpLiveData = MutableLiveData<String>()
	val deviceNumberLiveData = MutableLiveData<Int>()
	
	fun getServerIp() {
		val client = OkHttpClient()
		val request = Request.Builder()
			.url("https://api2.bmob.cn/1/classes/IP/PeCc888I")
			.addHeader("Content-Type", "application/json")
			.addHeader("X-Bmob-Application-Id", "2c3e4ae2e6b8e0abd27b500c95876716")
			.addHeader("X-Bmob-REST-API-Key", "8325c5621516619486f70c835a1e3347")
			.get()
			.build()
		client.newCall(request).enqueue(object : Callback {
			override fun onFailure(call: Call, e: IOException) {
			
			}
			
			override fun onResponse(call: Call, response: Response) {
				if (response.isSuccessful) {
					val result = response.body()?.string()
					val json = JSONObject(result)
					val ip = json.optString("ip")
					handler.post {
						serverIpLiveData.value = ip
					}
				}
			}
		})
	}
	
	fun createInitViewState(): MainViewState {
		val viewState = MainViewState()
		viewState.isLoading = true
		viewState.showCamera = false
		viewState.showNumber = false
		viewState.number = ""
		return viewState
	}
}