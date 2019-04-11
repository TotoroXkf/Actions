import java.net.Socket

fun execute(number: Int, deviceIp: String, action: String) {
    val socket = Socket(deviceIp, COMMAND_PORT)
    val writer = getSocketWriter(socket)
    writer.write(action)
    writer.flush()
    socket.shutdownOutput()

    when (action) {
        ACTION_CAPTURE -> {
            val reader = socket.getInputStream()
            val bytes = reader.readBytes()
            writeToLocal(bytes, number)
            socket.shutdownInput()
            reader.close()
        }
        ACTION_FINISH -> {

        }
        ACTION_ECHO -> {
            readAndPrint(socket, number)
        }
        else -> {
            readAndPrint(socket, number)
        }
    }
    writer.close()
    socket.close()
}

private fun readAndPrint(socket: Socket, number: Int) {
    val message = readMessage(socket)
    println("第 $number 台设备: $message")
}