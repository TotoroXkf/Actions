package com.example.camera

import android.util.Log
import java.io.*
import java.net.ServerSocket
import java.net.Socket

private const val mIpCollectPort = 12306
private const val mCommandPort = 12307

fun getSocketWriter(socket: Socket): PrintWriter {
	val outputStream = socket.getOutputStream()
	return PrintWriter(OutputStreamWriter(outputStream))
}

fun getSocketReader(socket: Socket): BufferedReader {
	val inputStream = socket.getInputStream()
	return BufferedReader(InputStreamReader(inputStream))
}

fun sendIpAndGetDeviceNumber(severIp: String, deviceIp: String): Int {
	val socket = Socket(severIp, mIpCollectPort)
	
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

fun runCommandSocket(handler: (String, OutputStream, () -> Unit) -> Unit) {
	Thread {
		val severSocket = ServerSocket(mCommandPort)
		while (true) {
			val socket = severSocket.accept()
			
			val reader = getSocketReader(socket)
			val command = reader.readLine()
			socket.shutdownInput()
			val writer = socket.getOutputStream()
			val closeSocket = {
				socket.shutdownOutput()
				reader.close()
				writer.close()
				socket.close()
			}
			handler(command, writer, closeSocket)
		}
	}.start()
}