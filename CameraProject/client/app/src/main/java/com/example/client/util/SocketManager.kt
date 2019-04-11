package com.example.client.util

import android.util.Log
import com.example.client.main.COMMAND_PORT
import com.example.client.main.IP_COLLECTOR_PORT
import org.greenrobot.eventbus.EventBus
import java.io.*
import java.lang.Exception
import java.net.InetSocketAddress
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
		Log.e("xkf123456789", "写入完毕")
		abortSavedSocket()
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
		Log.e("xkf123456789", "写入完毕")
		abortSavedSocket()
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
	
	socket.close()
	return deviceNumber.toInt()
}

fun runCommandSocket() {
	Thread {
		var severSocket: ServerSocket? = null
		try {
			severSocket = ServerSocket(COMMAND_PORT)
			severSocket.reuseAddress = true
			severSocket.bind(InetSocketAddress(COMMAND_PORT))
		} catch (e: Exception) {
		}
		while (true) {
			val socket = severSocket?.accept()
			if (socket != null) {
				EventBus.getDefault().post(socket)
			}
		}
	}.start()
}

fun abortSavedSocket() {
	if (savedSocket == null) {
		return
	}
	savedSocket!!.close()
	savedSocket = null
	if (savedReader != null) {
		savedReader = null
	}
}