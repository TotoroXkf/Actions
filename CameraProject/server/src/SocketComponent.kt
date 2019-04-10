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
    print("开启线程池等待设备接入")
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
            if ("all" == partner) {
                for (i in 1..deviceIpMap.size) {
                    if (i !in deviceIpMap) {
                        throw Exception()
                    }
                    val deviceIp = deviceIpMap[i]!!
                    cachedThreadPool.execute {
                        execute(i, deviceIp, action)
                    }
                }
            } else {
                val key = partner.toInt()
                if (key !in deviceIpMap) {
                    throw Exception()
                }
                val deviceIp = deviceIpMap[key]!!
                cachedThreadPool.execute {
                    execute(key, deviceIp, action)
                }
            }
        } catch (e: Exception) {
            println("无效输入")
            println(e.message)
        }
    }
}