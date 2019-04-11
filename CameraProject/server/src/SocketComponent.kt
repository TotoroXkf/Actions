import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors

@Volatile
private var deviceNum = 0
private val deviceIpMap = HashMap<Int, String>()

private val cachedThreadPool = Executors.newCachedThreadPool()

fun getSocketWriter(socket: Socket): PrintWriter {
    val outputStream = socket.getOutputStream()
    return PrintWriter(OutputStreamWriter(outputStream))
}

fun getSocketReader(socket: Socket): BufferedReader {
    val inputStream = socket.getInputStream()
    return BufferedReader(InputStreamReader(inputStream))
}

fun readMessage(socket: Socket): String {
    val reader = getSocketReader(socket)
    val message = reader.readText()
    socket.shutdownInput()
    reader.close()
    return message
}

class IpCollector(private val socket: Socket) : Runnable {
    override fun run() {
        deviceNum++
        println("第 $deviceNum 台设备接入")

        val writer = getSocketWriter(socket)
        writer.write("$deviceNum")
        writer.flush()
        socket.shutdownOutput()

        val reader = getSocketReader(socket)
        //格式：number号-ip地址
        val message = reader.readLine()
        val data = message.split("-")
        val number = data[0]
        val ipAddress = data[1]
        deviceIpMap[number.toInt()] = ipAddress
        println("已获取第 $number 台设备的ip地址")
        socket.shutdownInput()

        reader.close()
        writer.close()
        socket.close()
    }
}

fun runIpCollectTask() {
    println("开启线程池等待设备接入")
    Thread {
        val serverSocket = ServerSocket(IP_COLLECTOR_PORT)
        while (true) {
            val socket = serverSocket.accept()
            synchronized(serverSocket) {
                cachedThreadPool.execute(IpCollector(socket))
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
                    for ((key, value) in deviceIpMap.entries) {
                        cachedThreadPool.execute {
                            execute(key, value, action)
                        }
                    }
                }
                PARTNER_SELF -> {
                    executeBySelf(action)
                }
                else -> {
                    val key = partner.toInt()
                    if (key !in deviceIpMap) {
                        throw Exception()
                    }
                    val deviceIp = deviceIpMap[key]!!
                    cachedThreadPool.execute {
                        execute(key, deviceIp, action)
                    }
                }
            }
        } catch (e: Exception) {
            println("无效输入")
        }
    }
}

fun execute(number: Int, deviceIp: String, action: String) {
    val socket = Socket(deviceIp, COMMAND_PORT)
    val writer = getSocketWriter(socket)
    writer.write(action)
    writer.flush()
    socket.shutdownOutput()
    writer.close()

    when (action) {
        ACTION_CAPTURE -> {
            val reader = socket.getInputStream()
            val bytes = reader.readBytes()
            writeToLocal(bytes, number)
            socket.shutdownInput()
            reader.close()
        }
        ACTION_FINISH -> {
            deviceIpMap.remove(number)
        }
        ACTION_ECHO -> {
            readAndPrint(socket, number)
        }
        else -> {
            readAndPrint(socket, number)
        }
    }
    socket.close()
}

fun executeBySelf(action: String) {
    when (action) {
        ACTION_DEVICE_COUNT -> {
            println("目前连接的设备有:")
            for ((key, value) in deviceIpMap.entries) {
                println("设备号:$key---->ip:$value")
            }
        }
        else -> {

        }
    }
}

private fun readAndPrint(socket: Socket, number: Int) {
    val message = readMessage(socket)
    println("第 $number 台设备: $message")
}