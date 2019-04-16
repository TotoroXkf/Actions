import java.net.Socket

fun main() {
    val socket = Socket("127.0.0.1", 9999)
    socket.keepAlive = true
    while (true) {
        val input = socket.getInputStream()
        val bytes = ByteArray("xkf".toByteArray().size)
        input.read(bytes)
        println(String(bytes))
        val output = socket.getOutputStream()
        output.write("xkf".toByteArray())
        output.flush()
    }
}