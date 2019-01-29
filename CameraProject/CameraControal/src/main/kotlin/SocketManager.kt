import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors

private val cachedThreadPool = Executors.newCachedThreadPool()

@Volatile
private var deviceNum = 0
private val deviceIpMap = HashMap<Int, String>()

private const val ipCollectPort = 12306
private const val commandPort = 12307

fun getSocketWriter(socket: Socket): PrintWriter {
    val outputStream = socket.getOutputStream()
    return PrintWriter(OutputStreamWriter(outputStream))
}

fun getSocketReader(socket: Socket): BufferedReader {
    val inputStream = socket.getInputStream()
    return BufferedReader(InputStreamReader(inputStream))
}

fun runIpCollectTask() {
    Thread {
        val serverSocket = ServerSocket(ipCollectPort)
        while (true) {
            val socket = serverSocket.accept()
            synchronized(serverSocket) {
                cachedThreadPool.execute(IpCollector(socket))
            }
        }
    }.start()
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

fun dispatchCommand() {
    val scanner = Scanner(System.`in`)
    while (true) {
        // 格式
        // number/all-parameter-xxxxxx
        // number/all-action-xxxxxx
        val input = scanner.next()
        val number = input.split("-")[0]

        if (number == "all") {
            for (value in deviceIpMap.values) {
                cachedThreadPool.execute(CommandExecutor(value, input))
            }
        } else {
            val key = number.toInt()
            if (key in deviceIpMap) {
                cachedThreadPool.execute(CommandExecutor(deviceIpMap[key]!!, input))
            }
        }
    }
}

class CommandExecutor(private val deviceIp: String, private val command: String) :
    Runnable {
    override fun run() {
        val data = command.split("-")
        val deviceNumber = data[0].toInt()
        val type = data[1]
        val info = data[2]

        val socket = Socket(deviceIp, commandPort)

        val writer = getSocketWriter(socket)
        writer.write(command)
        writer.flush()
        socket.shutdownOutput()

        val inputStream = socket.getInputStream()
        val bytes = inputStream.readBytes()
        when (info) {
            "capture" -> {
                writeToLocal(bytes, deviceNumber)
            }
            else -> {
                val message = String(bytes)
                if (message == "OK") {
                    println("第 $deviceNumber 台设备执行完毕")
                }
            }
        }
        socket.shutdownInput()

        writer.close()
        inputStream.close()
        socket.close()
    }
}