import java.time.LocalDate

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> = object : Iterator<LocalDate> {
    var current = start

    override fun hasNext(): Boolean = current < endInclusive

    override fun next(): LocalDate = current.apply {
        current = plusDays(1)
    }
}

fun main() {
    val newYear = LocalDate.ofYearDay(2019, 100)
    val daysOff = newYear.minusDays(20)..newYear
    for (day in daysOff) {
        println(day)
    }
}