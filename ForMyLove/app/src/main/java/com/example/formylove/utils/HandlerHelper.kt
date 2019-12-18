package com.example.formylove.utils

import android.os.Handler
import android.os.Looper

object HandlerHelper {
    private val handler = Handler(Looper.getMainLooper())
    
    fun post(run: () -> Unit) {
        handler.post(run)
    }
    
    fun post(run: Runnable) {
        handler.post(run)
    }
    
    fun postDelay(delay: Long, run: Runnable) {
        handler.postDelayed(run, delay)
    }
    
    fun postDelay(delay: Long, run: () -> Unit) {
        handler.postDelayed(run, delay)
    }
}