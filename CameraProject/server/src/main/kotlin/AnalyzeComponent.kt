import kotlin.math.abs

val timeList = ArrayList<Long>()

fun timeAnalyze(time: Long, maxSize: Int) {
    synchronized(timeList) {
        timeList.add(time)
        if (timeList.size != maxSize) {
            return
        }
        println("分析数据中.....")
        val maxValue = timeList.max()!!
        val minValue = timeList.min()!!
        val timeDifference = abs(maxValue - minValue)
        println("本次拍摄最长的时间间隔为 $timeDifference ms")
        timeList.clear()
    }
}