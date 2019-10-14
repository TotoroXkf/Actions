import java.util.*

inline fun <reified T> isA(value: Any) {
    if (value is T) {
        println("success")
    } else {
        println("error")
    }
}

fun main() {
    isA<String>(1)
    isA<Calendar>(1)
}