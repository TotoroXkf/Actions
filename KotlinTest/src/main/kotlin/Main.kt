import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    try {
        method()
    } catch (e: java.lang.ArithmeticException) {
        println("Computation failed with ArithmeticException")
    }
}

suspend fun method(): Int = coroutineScope {
    val one = async<Int> {
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async<Int> {
        println("Second child throws an exception")
        throw ArithmeticException()
    }

    one.await() + two.await()
}


