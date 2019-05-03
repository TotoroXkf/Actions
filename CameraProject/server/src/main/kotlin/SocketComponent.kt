import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.HashMap
import kotlin.math.abs

@Volatile
private var deviceNum = 0

private val socketMap = HashMap<Int, Socket>()
private val readBufferMap = HashMap<Socket, BufferedInputStream>()
private val readerMap = HashMap<Socket, BufferedReader>()
private val writeBufferMap = HashMap<Socket, BufferedOutputStream>()
private val writerMap = HashMap<Socket, BufferedWriter>()

private val cachedThreadPool = Executors.newCachedThreadPool()

fun runSocketCollectTask() {
    println("开启线程池等待设备接入")
    Thread {
        val serverSocket = ServerSocket(SOCKET_PORT)
        while (true) {
            val socket = serverSocket.accept()
            synchronized(serverSocket) {
                deviceNum++
                println("接收到新的设备:$deviceNum")

                socket.keepAlive = true
                socketMap[deviceNum] = socket

                val inputStream = socket.getInputStream()
                val reader = BufferedReader(InputStreamReader(inputStream))
                readerMap[socket] = reader
                val readBuffer = BufferedInputStream(inputStream)
                readBufferMap[socket] = readBuffer

                val outPutStream = socket.getOutputStream()
                val writer = BufferedWriter(OutputStreamWriter(outPutStream))
                writerMap[socket] = writer
                val writeBuffer = BufferedOutputStream(outPutStream)
                writeBufferMap[socket] = writeBuffer

                sendMessage(socket, "$deviceNum")
            }
        }
    }.start()
}

fun dispatchCommand() {
    val scanner = Scanner(System.`in`)
    while (true) {
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
                var number = -1
                try {
                    number = partner.toInt()
                } catch (e: Exception) {
                }
                if (number !in socketMap) {
                    println("无效输入")
                } else {
                    cachedThreadPool.execute {
                        execute(number, action)
                    }
                }
            }
        }
    }
}

private fun execute(number: Int, command: String) {
    if (number !in socketMap) {
        return
    }
    val socket = socketMap[number]!!
    if (socket.isClosed) {
        println("第 $number 台设备已经关闭")
        return
    }

    val action: String
    val index = command.indexOf("?")
    action = if (index > 0) {
        command.substring(0, index)
    } else {
        command
    }

    when (action) {
        ACTION_CAPTURE -> {
            val starTime = System.currentTimeMillis()
            sendMessage(socket, command)
            val message = readMessage(socket)
            if (message == OK) {
                println("第 $number 台设备拍摄完毕")
            }

            val clientConsumeTime = readMessage(socket).toLong()
            timeAnalyze(starTime, clientConsumeTime, socketMap.size)
        }
        ACTION_GET -> {
            sendMessage(socket, command)
            println("正在接受第 $number 台设备发来的数据......")
            val bytes = readBytes(socket)
            writeBytesToLocal(bytes, number)
        }
        ACTION_FINISH -> {
            sendMessage(socket, command)
            closeSocket(number)
        }
        ACTION_DELAY_TEST -> {
            val startTime = System.currentTimeMillis()
            sendMessage(socket, command)
            val message = readMessage(socket)
            if (message == OK) {
                val endTime = System.currentTimeMillis()
                println("第 $number 台设备: ${abs(endTime - startTime)} ms")
            }
        }
        ACTION_ECHO -> {
            sendMessage(socket, command)
            println("第 $number 台设备: ${readMessage(socket)}")
        }
        ACTION_REMOVE -> {
            closeSocket(number)
        }
        ACTION_ZOOM, ACTION_FLASH, ACTION_HDR, ACTION_WHITE_BALANCE -> {
            sendMessage(socket, command)
            val message = readMessage(socket)
            if (message != OK) {
                println("第 $number 台设备参数设置失败！")
            }
        }
        else -> {
            println("没有相关的命令")
        }
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
    val writer = writerMap[socket]!!
    writer.write(message)
    writer.newLine()
    writer.flush()
}

fun readMessage(socket: Socket): String {
    val reader = readerMap[socket]!!
    return reader.readLine()
}

fun readBytes(socket: Socket): ByteArray {
    val readBuffer = readBufferMap[socket]!!
    val size = readMessage(socket).toInt()
    var len = 1024
    var count = 0
    val bytes = ByteArray(size)
    while (count < size) {
        val num = readBuffer.read(bytes, count, len)
        count += num
        if (size - count < len) {
            len = size - count
        }
        sendMessage(socket, OK)
    }
    return bytes
}

fun closeSocket(number: Int) {
    if (number !in socketMap) {
        return
    }
    val socket = socketMap[number]!!
    socket.close()
    socketMap.remove(number)
    readBufferMap.remove(socket)
    readerMap.remove(socket)
    writeBufferMap.remove(socket)
    writerMap.remove(socket)
    println("第 $number 台设备被移除")
}