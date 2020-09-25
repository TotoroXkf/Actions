import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

/**
 * 最顶层的抽象接口
 */
interface Dispatcher {
    fun dispatch(block: () -> Unit)
}

/**
 * 调度器的集合类
 */
object Dispatchers {
    val Default by lazy {
        DispatcherContext(DefaultDispatcher)
    }
}

/**
 * 调度器协程上下文，本质是一个拦截器
 */
class DispatcherContext(private val dispatcher: Dispatcher) : AbstractCoroutineContextElement(ContinuationInterceptor),
    ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        DispatchedContinuation(continuation, dispatcher)
}

/**
 * 调度器协程的实现类
 */
class DispatchedContinuation<T>(private val delegate: Continuation<T>, private val dispatcher: Dispatcher) :
    Continuation<T> {
    override val context = delegate.context

    /**
     * 按照来解其的原理，在协程开始的时候和被恢复的时候各执行一次
     * 在每次执行的时候切换线程即可
     */
    override fun resumeWith(result: Result<T>) {
        // 线程切换
        dispatcher.dispatch {
            // 恢复协程执行
            delegate.resumeWith(result)
        }
    }
}

/**
 * 一个简单的基于线程池的实现类
 */
object DefaultDispatcher : Dispatcher {
    private val threadGroup = ThreadGroup("DefaultDispatcher")
    private val threadIndex = AtomicInteger(0)
    private val executor = Executors.newFixedThreadPool(1) { runnable ->
        Thread(threadGroup, runnable, "${threadGroup.name}-worker-${threadIndex.getAndIncrement()}").apply {
            isDaemon = true
        }
    }

    override fun dispatch(block: () -> Unit) {
        executor.submit(block)
    }
}