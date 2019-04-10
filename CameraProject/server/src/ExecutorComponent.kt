import java.net.Socket
import java.util.concurrent.Executors

private val cachedThreadPool = Executors.newCachedThreadPool()

fun execute(deviceIp: String, action: String) {
    val socket = Socket(deviceIp,COMMAND_PORT)
}