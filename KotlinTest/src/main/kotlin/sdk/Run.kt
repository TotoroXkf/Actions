package sdk

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val broadcastChannel = BroadcastChannel<Int>(Channel.BUFFERED)
    GlobalScope.launch {
        repeat(3) {
            delay(100)
            broadcastChannel.send(it)
        }
        broadcastChannel.close()
    }
    repeat(3) {
        GlobalScope.launch {
            val receiveChannel = broadcastChannel.openSubscription()
            for (i in receiveChannel) {
                println("[#$it] received: $i")
            }
        }
    }
    delay(100000)
}