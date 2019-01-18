import java.io.*
import java.net.Socket

fun main(args: Array<String>) {

    for (i in 1..50) {
        val socket = Socket("127.0.0.1", 12306)

        val inputStream = socket.getInputStream()
        val reader = BufferedReader(InputStreamReader(inputStream))
        val message = reader.readLine()
        println("收到了服务端的消息--->$message")
        socket.shutdownInput()

        val outputStream = socket.getOutputStream()
        val writer = PrintWriter(OutputStreamWriter(outputStream))
        writer.write("$message-192.168.100.1")
        writer.flush()
        socket.shutdownOutput()

        outputStream.close()
        writer.close()
        inputStream.close()
        reader.close()
        socket.close()
    }
}