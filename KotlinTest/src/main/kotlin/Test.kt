import kotlin.concurrent.thread
import kotlin.coroutines.*

suspend fun main() {
    launch(Dispatchers.Default) {
        println(1)
        delay(1000)
        println(2)
    }
}