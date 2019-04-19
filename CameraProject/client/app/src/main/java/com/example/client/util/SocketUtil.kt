package com.example.client.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.client.main.SOCKET_PORT
import com.example.client.main.OK
import java.io.*
import java.lang.Exception
import java.net.Socket

lateinit var savedSocket: Socket
lateinit var readBuffer: BufferedInputStream
lateinit var writeBuffer: BufferedOutputStream
lateinit var reader: BufferedReader
lateinit var writer: BufferedWriter

fun connectToServer(ip: String, liveData: MutableLiveData<Int>) {
	savedSocket = Socket(ip, SOCKET_PORT)
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

fun sendBytes(bytes: ByteArray) {
	val size = bytes.size
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
		if (result != OK) {
			throw Exception()
		}
	}
}

fun readMessage(): String {
	return reader.readLine()
}

fun disConnect() {
	if (!savedSocket.isClosed) {
		savedSocket.close()
	}
}