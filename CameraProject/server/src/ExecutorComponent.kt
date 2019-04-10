import java.net.Socket

fun execute(number:Int,deviceIp: String, action: String) {
    val socket = Socket(deviceIp, COMMAND_PORT)
    val writer = getSocketWriter(socket)
    writer.write(action)
    writer.flush()
    socket.shutdownOutput()

    when (action) {
        ACTION_CAPTURE -> {
            val reader = socket.getInputStream()
            val bytes = reader.readBytes()
            writeToLocal(bytes,number)
            socket.shutdownInput()
            reader.close()
        }
        else -> {

        }
    }

    writer.close()
    socket.close()
}