package com.example.camera

import android.content.Context
import android.util.Log
import com.avos.avoscloud.*

fun leanCloudInit(context: Context) {
	val applicationId = "KW6VheWbtqFJLoUJp9FWgNkS-gzGzoHsz"
	val clientKey = "pkdvhhcIr0w0HwLW7amBeFtv"
	
	AVOSCloud.initialize(context, applicationId, clientKey)
	AVOSCloud.setDebugLogEnabled(true);
}

fun getServerIpAddress(handler: (String) -> Unit) {
	val objectId = "5c4034cc7565710068a13f4f"
	
	val avQuery = AVQuery<AVObject>("IP")
	avQuery.getInBackground(objectId, object : GetCallback<AVObject>() {
		override fun done(avObject: AVObject?, e: AVException?) {
			handler(avObject!!.getString("IpAddress"))
		}
	})
}