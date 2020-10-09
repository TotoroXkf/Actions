package sdk

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
fun main() = runBlocking {
    var count = 0
    val semaphore = Semaphore(1)
    List(1000) {
        GlobalScope.launch {
            semaphore.withPermit {
                count++;
            }
        }
    }.joinAll()

    println(count)

    delay(1000)
}
