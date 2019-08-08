import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

fun main() {
    GlobalScope.launch {
        kotlinx.coroutines.delay(500)
        println("xkf")
    }
    GlobalScope.launch {
        kotlinx.coroutines.delay(100)
        println("zs")
    }
}