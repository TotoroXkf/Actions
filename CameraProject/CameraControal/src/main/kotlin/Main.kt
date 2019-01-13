import java.net.ServerSocket
import java.util.*


fun main(args: Array<String>) {
    val severSocket = ServerSocket(10086)
    val scanner = Scanner(System.`in`)
    while (true) {
        val socket = severSocket.accept()
        println("服务端收到响应")
        val info = scanner.next()
        val os = socket.getOutputStream()//服务器端产生的Socket获取输出流
        os.write(info.toByteArray())
        os.close()
        socket.close()
    }
}