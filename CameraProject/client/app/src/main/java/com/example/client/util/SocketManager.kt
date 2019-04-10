package com.example.client.util

import android.util.Log
import com.example.client.main.COMMAND_PORT
import com.example.client.main.IP_COLLECTOR_PORT
import org.greenrobot.eventbus.EventBus
import java.io.*
import java.net.ServerSocket
import java.net.Socket



fun getSocketWriter(socket: Socket): PrintWriter {
	val outputStream = socket.getOutputStream()
	return PrintWriter(OutputStreamWriter(outputStream))
}

fun getSocketReader(socket: Socket): BufferedReader {
	val inputStream = socket.getInputStream()
	return BufferedReader(InputStreamReader(inputStream))
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