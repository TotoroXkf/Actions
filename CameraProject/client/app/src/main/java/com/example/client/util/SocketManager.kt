package com.example.client.util

import android.util.Log
import com.example.client.main.COMMAND_PORT
import com.example.client.main.IP_COLLECTOR_PORT
import org.greenrobot.eventbus.EventBus
import java.io.*
import java.net.ServerSocket
import java.net.Socket

var savedSocket: Socket? = null
var savedReader: Reader? = null

fun getSocketWriter(socket: Socket): PrintWriter {
	val outputStream = socket.getOutputStream()
	return PrintWriter(OutputStreamWriter(outputStream))
}

fun getSocketReader(socket: Socket): BufferedReader {
	val inputStream = socket.getInputStream()
	return BufferedReader(InputStreamReader(inputStream))
}

fun saveSocketAndReader(socket: Socket, reader: Reader) {
	savedReader = reader
	savedSocket = socket
}

fun writeString(message: String) {
	if (savedSocket == null || savedReader == null) {
		return
	}
	Thread {
		Log.e("xkf123456789", "开始写入")
		val writer = getSocketWriter(savedSocket!!)
		writer.write(message)
		writer.flush()
		savedSocket!!.shutdownOutput()
		writer.close()
		Log.e("xkf123456789", "写入完毕")
		abortSocketAndReader()
	}.start()
}

fun writeBytes(bytes: ByteArray) {
	if (savedSocket == null || savedReader == null) {
		return
	}
	Thread {
		Log.e("xkf123456789", "开始写入")
		val writer = savedSocket!!.getOutputStream()
		writer.write(bytes)
		writer.flush()
		savedSocket!!.shutdownOutput()
		writer.close()
		Log.e("xkf123456789", "写入完毕")
		abortSocketAndReader()
	}.start()
}

fun sendIpAndGetDeviceNumber(severIp: String, deviceIp: String): Int {
	
	val socket = Socket(severIp, IP_COLLECTOR_PORT)
	
	val reader = getSocketReader(socket)
	val deviceNumber = reader.readLine()
	Log.e("xkf123456789", "获取本机Number$deviceNumber")
	socket.shutdownInput()
	
	val writer = getSocketWriter(socket)
	writer.write("$deviceNumber-$deviceIp")
	writer.flush()
	Log.e("xkf123456789", "发送本机IP$deviceIp")
	socket.shutdownOutput()
	
	writer.close()
	reader.close()
	socket.close()
	
	return deviceNumber.toInt()
}

fun runCommandSocket() {
	Thread {
		val severSocket = ServerSocket(COMMAND_PORT)
		while (true) {
			Log.e("xkf123456789", "等待新的命令")
			val socket = severSocket.accept()
			EventBus.getDefault().post(socket)
		}
	}.start()
}

fun abortSocketAndReader() {
	if (savedSocket == null) {
		return
	}
	savedSocket!!.close()
	savedSocket = null
	if (savedReader == null) {
		return
	}
	savedReader!!.close()
	savedReader = null
}