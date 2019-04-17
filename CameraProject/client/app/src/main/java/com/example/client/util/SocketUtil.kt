package com.example.client.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.client.main.IP_COLLECTOR_PORT
import java.io.*
import java.net.Socket

lateinit var savedSocket: Socket

fun connectToServer(ip: String, liveData: MutableLiveData<Int>) {
	savedSocket = Socket(ip, IP_COLLECTOR_PORT)
	Log.e("xkf123456789", "连接到服务端")
	savedSocket.keepAlive = true
	Log.e("xkf123456789", "等待接收编号")
	val message = readMessage(savedSocket)
	Log.e("xkf123456789", "收到编号")
	liveData.postValue(message.toInt())
}

fun sendMessage(message: String) {
	sendMessage(savedSocket, message)
}

fun sendMessage(socket: Socket, message: String) {
	val outputStream = socket.getOutputStream()
	val writer = PrintWriter(OutputStreamWriter(outputStream))
	writer.write(message)
	writer.flush()
}

fun sendPicture(socket: Socket, bytes: ByteArray) {
	val outputStream = socket.getOutputStream()
	outputStream.write(bytes)
	outputStream.flush()
}

fun readMessage(): String {
	return readMessage(savedSocket)
}

fun readMessage(socket: Socket): String {
	val inputStream = socket.getInputStream()
	val reader = BufferedReader(InputStreamReader(inputStream))
	return reader.readLine()
}

fun readBytes(socket: Socket): ByteArray {
	val inputStream = socket.getInputStream()
	return inputStream.readBytes()
}

fun disConnect() {
	if (!savedSocket.isClosed) {
		savedSocket.close()
	}
}