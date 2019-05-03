import java.io.*
import kotlin.math.abs
import java.text.SimpleDateFormat
import java.util.*


fun writeBytesToLocal(bytes: ByteArray, number: Int) {
    val dir = File("pictures")
    if (!dir.exists()) {
        dir.mkdir()
    }
    val file = File("pictures/$number.jpg")
    if (!file.exists()) {
        file.createNewFile()
    }
    val fileOutStream = FileOutputStream(file)
    fileOutStream.write(bytes)
    fileOutStream.close()
    println("写入完毕: " + file.path)
}

fun writeLog(timeList: ArrayList<Long>) {
    println("数据日志写入中.....")
    timeList.sort()

    val dir = File("log")
    if (!dir.exists()) {
        dir.mkdir()
    }
    val file = File("log/log.txt")
    if (!file.exists()) {
        file.createNewFile()
    }

    val writer = FileWriter(file, true)
    writer.run {
        write("\n")
        write("\n")

        //记录当前时间
        write("记录时间： ")
        val sdf = SimpleDateFormat()
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a")
        val date = Date()
        write(sdf.format(date))
        write("\n")

        //记录设备数
        write("设备数： ${timeList.size}\n")

        //记录时间顺序的时间戳
        write("拍摄时间戳如下：\n")
        write("--------------\n")
        for (time in timeList) {
            write(time.toString())
            write("\n")
        }
        write("--------------\n")

        //记录最大间隔
        write("最大时间间隔： ")
        val maxValue = timeList.max()!!
        val minValue = timeList.min()!!
        val timeDifference = abs(maxValue - minValue)
        write(timeDifference.toString())
        write("\n")

        close()
    }
    println("数据记录完毕")
}