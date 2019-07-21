import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    launch {
        kotlinx.coroutines.delay(1000)
        println("World")
    }
    println("Hello")
}

//fun main() {
//    GlobalScope.launch {
//        launch {
//            kotlinx.coroutines.delay(1000)
//            println("World")
//        }
//        println("Hello")
//    }
//
//    Thread.sleep(2000)
//}
