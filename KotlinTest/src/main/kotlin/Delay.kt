import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(1) {
    Thread(it, "Scheduler").apply { isDaemon = true }
}

suspend fun delay(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS) {
    if (time <= 0) {
        return
    }
    suspendCoroutine<Unit> { coroutine ->
        executor.schedule({ coroutine.resume(Unit) }, time, unit)
    }
}