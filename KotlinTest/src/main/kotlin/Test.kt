import kotlin.concurrent.thread
import kotlin.coroutines.*

fun main() {
    suspend {
        suspendFunc02()
        suspendFunc02()
        20
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = LogInterceptor()

        override fun resumeWith(result: Result<Int>) {
            println("end")
            println("result : " + result.getOrDefault(0))
        }
    })
}

suspend fun suspendFunc02() {
    suspendCoroutine<Int> { continuation ->
        thread {
            Thread.sleep(1000)
            continuation.resumeWith(Result.success(5))
        }
    }
}

class LogInterceptor : ContinuationInterceptor {
    override val key: CoroutineContext.Key<*>
        get() = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        LogContinuation(continuation)
}

class LogContinuation<T>(private val continuation: Continuation<T>) : Continuation<T> by continuation {
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith: $result")
        continuation.resumeWith(result)
        println("after resumeWith.")
    }
}