package com.example.client.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.client.main.IP_COLLECTOR_PORT
import okio.Okio
import java.io.*
import java.net.Socket

lateinit var savedSocket: Socket

fun connectToServer(ip: String, liveData: MutableLiveData<Int>) {
	savedSocket = Socket(ip, IP_COLLECTOR_PORT)
	Log.e("xkf123456789", "连接到服务端")
	savedSocket.keepAlive = true
	val message = readMessage()
	liveData.postValue(message.toInt())
}

fun sendMessage(message: String) {
	val outputStream = savedSocket.getOutputStream()
	val writer = BufferedWriter(OutputStreamWriter(outputStream))
	writer.write(message)
	writer.newLine()
	writer.flush()
}

fun sendPicture(bytes: ByteArray) {
	val outputStream = savedSocket.getOutputStream()
	
	val writer = BufferedWriter(OutputStreamWriter(outputStream))
	writer.write("${bytes.size}")
	writer.newLine()
	writer.flush()
	
	outputStream.write(bytes)
	outputStream.flush()
}

fun readMessage(): String {
	val inputStream = savedSocket.getInputStream()
	val reader = BufferedReader(InputStreamReader(inputStream))
	return reader.readLine()
}

fun disConnect() {
	if (!savedSocket.isClosed) {
		savedSocket.close()
	}
}