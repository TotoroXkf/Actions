import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors

val socketMap = HashMap<Int, Socket>()
var number = 0

val threadPool = Executors.newCachedThreadPool()

fun main() {
    start()
    val scanner = Scanner(System.`in`)
    while (true) {
        val str = scanner.next()
        for ((number, socket) in socketMap) {
            threadPool.execute {
                val output = socket.getOutputStream()
                output.write("xkf".toByteArray())
                output.flush()
                val input = socket.getInputStream()
                val bytes = ByteArray("xkf".toByteArray().size)
                input.read(bytes)
                println("$number : " + String(bytes))
            }
        }
    }
}

fun start() {
    Thread {
        val serverSocket = ServerSocket(9999)
        while (true) {
            val socket = serverSocket.accept()
            number++
            println(number)
            socket.keepAlive = true
            socketMap[number] = socket
        }
    }.start()
}
