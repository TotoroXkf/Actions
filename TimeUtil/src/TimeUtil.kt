import java.util.*

fun main() {
    val startTime = "2019年7月22日23时"
    val endTime = "2019年7月26日9时"

    val startDate = getDate(startTime)
    val endDate = getDate(endTime)

    val startCalendar = getCalenderDate(Calendar.getInstance(), startDate)
    val endCalendar = getCalenderDate(Calendar.getInstance(), endDate)
    val result = (endCalendar.time.time - startCalendar.time.time) / 1000 / 3600
    println("距离下次见面还有${result}个小时")
}

fun getDate(time: String): IntArray {
    val yearIndex = time.indexOf("年")
    val monthIndex = time.indexOf("月")
    val dayIndex = time.indexOf("日")
    val hourIndex = time.indexOf("时")

    val year = time.substring(0, yearIndex).toInt()
    val month = time.substring(yearIndex + 1, monthIndex).toInt()
    val day = time.substring(monthIndex + 1, dayIndex).toInt()
    val hour = time.substring(dayIndex + 1, hourIndex).toInt()

    return intArrayOf(year, month, day, hour)
}

fun getCalenderDate(calendar: Calendar, date: IntArray): Calendar {
    calendar.set(Calendar.YEAR, date[0])
    calendar.set(Calendar.MONTH, date[1] - 1)
    calendar.set(Calendar.DAY_OF_MONTH, date[2])
    calendar.set(Calendar.HOUR_OF_DAY, date[3])
    return calendar
}