import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import java.lang.ArithmeticException
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {

    val job = launch {
        println("1")
        yield()
        println("2")
    }

    println("3")
    println("4")
    job.join()
    println("5")
}
