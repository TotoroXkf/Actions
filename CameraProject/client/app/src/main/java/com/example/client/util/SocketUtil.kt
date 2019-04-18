package com.example.client.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.client.main.IP_COLLECTOR_PORT
import java.io.*
import java.lang.Exception
import java.net.Socket

lateinit var savedSocket: Socket
lateinit var readBuffer: BufferedInputStream
lateinit var writeBuffer: BufferedOutputStream
lateinit var reader: BufferedReader
lateinit var writer: BufferedWriter

fun connectToServer(ip: String, liveData: MutableLiveData<Int>) {
	savedSocket = Socket(ip, IP_COLLECTOR_PORT)
	Log.e("xkf123456789", "连接到服务端")
	savedSocket.keepAlive = true
	
	val inputStream = savedSocket.getInputStream()
	reader = BufferedReader(InputStreamReader(inputStream))
	readBuffer = BufferedInputStream(inputStream)
	
	val outPutStream = savedSocket.getOutputStream()
	writer = BufferedWriter(OutputStreamWriter(outPutStream))
	writeBuffer = BufferedOutputStream(outPutStream)
	
	val message = readMessage()
	liveData.postValue(message.toInt())
}

fun sendMessage(message: String) {
	writer.write(message)
	writer.newLine()
	writer.flush()
}

fun sendPicture(bytes: ByteArray) {
	val size = bytes.size
	Log.e("xkf123456789", "$size")
	sendMessage("${bytes.size}")

	var len = 1024
	var count = 0
	while (count < size) {
		writeBuffer.write(bytes, count, len)
		writeBuffer.flush()
		count += len
		if (size - count < len) {
			len = size - count
		}
		val result = readMessage()
		if (result != "OK") {
			throw Exception()
		}
	}
	writeBuffer.write(bytes)
	writeBuffer.flush()
}

fun readMessage(): String {
	return reader.readLine()
}

fun disConnect() {
	if (!savedSocket.isClosed) {
		savedSocket.close()
	}
}