package com.example.camera

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager


fun getDeviceIp(context: Context): String {
	val info =
		(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
	if (info != null && info.isConnected) {
		if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
			val wifiManager =
				context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
			val wifiInfo = wifiManager.connectionInfo
			return intIP2StringIP(wifiInfo.ipAddress)
		}
	}
	return "0.0.0.0"
}

private fun intIP2StringIP(ip: Int): String {
	return (ip and 0xFF).toString() + "." +
			(ip shr 8 and 0xFF) + "." +
			(ip shr 16 and 0xFF) + "." +
			(ip shr 24 and 0xFF)
}