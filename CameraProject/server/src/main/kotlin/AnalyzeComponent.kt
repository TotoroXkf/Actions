import kotlin.math.abs

val timeList = ArrayList<Long>()

fun timeAnalyze(startTime: Long, clientConsumeTime: Long, maxSize: Int) {
    val currentTime = System.currentTimeMillis()
    synchronized(timeList) {
        val rttConsumeTime = ((currentTime - startTime) - clientConsumeTime) / 2
        val time = startTime + rttConsumeTime
        timeList.add(time)
        if (timeList.size != maxSize) {
            return
        }
        println("分析数据中.....")

        println("本次间隔最大时延为：${getMaxTimeDifference(timeList)} ms")
        writeLog(timeList)
        timeList.clear()
    }
}

fun getMaxTimeDifference(timeList: ArrayList<Long>): Long {
    val maxValue = timeList.max()!!
    val minValue = timeList.min()!!
    return abs(maxValue - minValue)
}