import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val result = async(start = CoroutineStart.LAZY){
        kotlinx.coroutines.delay(1000)
        101
    }
    result.start()
    println("result:"+result.await())
}