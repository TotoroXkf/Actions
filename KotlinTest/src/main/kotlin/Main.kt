import kotlin.coroutines.*
import kotlinx.coroutines.*

fun main() = runBlocking {
    var count = 0;
    //使用预置的线程池
    withContext(Dispatchers.Default) {
        val n = 10
        val k = 10
        repeat(n) {
            launch {
                //每一次都会从线程池里取一个线程
                repeat(k) {
                    //相当于多线程并发
                    println(Thread.currentThread().name)
                    count++
                }
            }
        }
    }
    println(count)
}