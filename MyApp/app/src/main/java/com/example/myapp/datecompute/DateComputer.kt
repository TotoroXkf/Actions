package com.example.myapp.datecompute

import java.lang.Exception
import java.util.*

const val ERROR = "Error"

/*
 * 计算从今天往后x天的日期
 */
fun computeAfterSomeDays(after: String): String {
    return try {
        val afterDays = after.toInt()
        val now = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"))
        now.add(Calendar.DAY_OF_YEAR, afterDays)
        "${now.get(Calendar.YEAR)}" +
                "-${now.get(Calendar.MONTH) + 1}" +
                "-${now.get(Calendar.DAY_OF_MONTH)}" +
                "-${getDayOfWeekString(now.get(Calendar.DAY_OF_WEEK))}"
    } catch (e: Exception) {
        ERROR
    }
}

/*
 * 计算从今天到某一天的间隔
 */
fun computeDaysFromNow(date: String): String {
    val dates = date.split("-")
    return if (checkInput(dates)) {
        val now = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"))
        val target = getTargetCalendar(dates)
        val result = Math.abs((target.time.time - now.time.time) / 1000 / 60 / 60 / 24)
        result.toString()
    } else {
        ERROR
    }
}


/*
 * 计算从某一天之后x天的日期
 */
fun computeAfterSomeDaysFromNow(date: String, after: String): String {
    val dates = date.split("-")
    if (!checkInput(dates)) {
        return ERROR
    }
    return try {
        val afterDays = after.toInt()
        val start = getTargetCalendar(dates)
        start.add(Calendar.DAY_OF_YEAR, afterDays)
        "${start.get(Calendar.YEAR)}" +
                "-${start.get(Calendar.MONTH) + 1}" +
                "-${start.get(Calendar.DAY_OF_MONTH)}" +
                "-${getDayOfWeekString(start.get(Calendar.DAY_OF_WEEK))}"
    } catch (e: Exception) {
        ERROR
    }
}

/*
 * 计算从某一天到某一天之间的间隔
 */
fun computeSomeDaysFromSomeDay(startDate: String, endDate: String): String {
    val startDates = startDate.split("-")
    val endDates = endDate.split("-")
    if (!checkInput(startDates) || !checkInput(endDates)) {
        return ERROR
    }
    val start = getTargetCalendar(startDates)
    val end = getTargetCalendar(endDates)
    val result = Math.abs((start.time.time - end.time.time) / 1000 / 60 / 60 / 24)
    return result.toString()
}

/*
 * 计算对应的是星期几
 */
private fun getDayOfWeekString(dayOfWeek: Int): String {
    return when (dayOfWeek) {
        Calendar.MONDAY -> "星期一"
        Calendar.TUESDAY -> "星期二"
        Calendar.WEDNESDAY -> "星期三"
        Calendar.THURSDAY -> "星期四"
        Calendar.FRIDAY -> "星期五"
        Calendar.SATURDAY -> "星期六"
        else -> "星期天"
    }
}

/*
 * 设置一个指定的Calendar对象
 */
fun getTargetCalendar(dates: List<String>): Calendar {
    val calendar = Calendar.getInstance()
    calendar.apply {
        set(Calendar.YEAR, dates[0].toInt())
        set(Calendar.MONTH, getMonth(dates[1].toInt()))
        set(Calendar.DAY_OF_MONTH, dates[2].toInt())
    }
    return calendar
}


/*
 * 把输入的数字转换为Calendar对应的月份
 */
private fun getMonth(month: Int): Int {
    return when (month) {
        1 -> Calendar.JANUARY
        2 -> Calendar.FEBRUARY
        3 -> Calendar.MARCH
        4 -> Calendar.APRIL
        5 -> Calendar.MAY
        6 -> Calendar.JUNE
        7 -> Calendar.JULY
        8 -> Calendar.AUGUST
        9 -> Calendar.SEPTEMBER
        10 -> Calendar.OCTOBER
        11 -> Calendar.NOVEMBER
        else -> Calendar.DECEMBER
    }
}

/*
 * 检查输入是否有效,触发以下任何一条都视作无效输入
 * - 传入的字符数组长度不等于3
 * - 输入的日，月，年中有不是数字的
 * - 日或者月小于等于0，年小于0
 * - 月超过12
 * - 任何天数超过31天
 * - 非闰年2月超过28天，闰年超过29天
 * - 1,3,5,7,8,10,12 非这些月份超过30天
 */
fun checkInput(dates: List<String>): Boolean {
    if (dates.size != 3) {
        return false
    }

    val year: Int
    val month: Int
    val day: Int
    try {
        year = dates[0].toInt()
        month = dates[1].toInt()
        day = dates[2].toInt()
    } catch (e: Exception) {
        return false
    }

    if (year < 0 || (day <= 0 || month <= 0)) {
        return false
    }

    if (month > 12 || day > 31) {
        return false
    }

    val specialMonth = arrayOf(1, 3, 5, 7, 8, 10, 12)
    fun isSpecialYear(y: Int): Boolean {
        var isLeapYear = false
        if (y % 4 == 0 && y % 100 != 0) {
            isLeapYear = true
        } else if (y % 400 == 0) {
            isLeapYear = true
        }
        return isLeapYear
    }

    if (month == 2) {
        val isSpecialYear = isSpecialYear(year)
        if (!isSpecialYear && day > 28) {
            return false
        }
        if (isSpecialYear && day > 29) {
            return false
        }
    }

    if (day > 30 && month !in specialMonth) {
        return false
    }

    return true
}
