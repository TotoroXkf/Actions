import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    var count = 0;
    val mutex = Mutex()
    withContext(Dispatchers.Default) {
        val n = 100
        val k = 1000
        repeat(n) {
            launch {
                repeat(k) {
                    mutex.withLock {
                        println(Thread.currentThread().name)
                        count++
                    }
                }
            }
        }
    }
    println(count)
}
