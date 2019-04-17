import java.io.*
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.HashMap

@Volatile
private var deviceNum = 0
private val socketMap = HashMap<Int, Socket>()

private val cachedThreadPool = Executors.newCachedThreadPool()

fun runSocketCollectTask() {
    println("开启线程池等待设备接入")
    Thread {
        val serverSocket = ServerSocket(IP_COLLECTOR_PORT)
        while (true) {
            val socket = serverSocket.accept()
            synchronized(serverSocket) {
                deviceNum++
                println("接收到新的设备:$deviceNum")
                socket.keepAlive = true
                socketMap[deviceNum] = socket
                println("发送设备号")
                sendMessage(socket, "$deviceNum\n")
                println("发送完毕")
            }
        }
    }.start()
}

fun dispatchCommand() {
    val scanner = Scanner(System.`in`)
    while (true) {
        try {
            //格式
            //设备的number或者是all - 动作
            val str = scanner.next()
            if (str == "") {
                throw Exception()
            }
            val command = str.split("-")
            val partner = command[0]
            val action = command[1]
            when (partner) {
                PARTNER_ALL -> {
                    for (number in socketMap.keys) {
                        cachedThreadPool.execute {
                            execute(number, action)
                        }
                    }
                }
                PARTNER_SELF -> {
                    executeBySelf(action)
                }
                else -> {
                    val number = partner.toInt()
                    if (number !in socketMap) {
                        throw Exception()
                    }
                    cachedThreadPool.execute {
                        execute(number, action)
                    }
                }
            }
        } catch (e: Exception) {
            println("无效输入")
        }
    }
}

private fun execute(number: Int, action: String) {
    try {
        if (number !in socketMap) {
            return
        }
        val socket = socketMap[number]!!
        when (action) {
            ACTION_CAPTURE -> {
                sendMessage(socket, action)
                val bytes = readBytes(socket)
                writeToLocal(bytes, number)
            }
            ACTION_FINISH -> {
                sendMessage(socket, action)
                socket.close()
                socketMap.remove(number)
            }
            ACTION_DELAY_TEST -> {
                sendMessage(socket, action + "?time=" + System.currentTimeMillis().toString())
                val time = readMessage(socket)
                println("设备 $number 接收延迟: $time ms")
            }
            ACTION_ECHO -> {
                sendMessage(socket, action)
                println("第 $number 台设备: ${readMessage(socket)}")
            }
            else -> {
                sendMessage(socket, action)
                println("第 $number 台设备: ${readMessage(socket)}")
            }
        }
    } catch (e: Exception) {
        println("ERROR!!!")
    }
}

private fun executeBySelf(action: String) {
    when (action) {
        ACTION_DEVICE_COUNT -> {
            println("目前连接的设备有: ${socketMap.size} 个")
        }
        else -> {

        }
    }
}

fun sendMessage(socket: Socket, message: String) {
    val outputStream = socket.getOutputStream()
    val writer = PrintWriter(OutputStreamWriter(outputStream))
    writer.write(message)
    writer.flush()
}

fun readMessage(socket: Socket): String {
    val inputStream = socket.getInputStream()
    val reader = BufferedReader(InputStreamReader(inputStream))
    return reader.readLine()
}

fun readBytes(socket: Socket): ByteArray {
    val inputStream = socket.getInputStream()
    return inputStream.readBytes()
}
