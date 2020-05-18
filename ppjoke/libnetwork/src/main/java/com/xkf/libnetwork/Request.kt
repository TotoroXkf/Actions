package com.xkf.libnetwork

import androidx.annotation.IntDef
import okhttp3.Request

abstract class Request<T, R : Request>(var url: String) {
    companion object {
        const val CACHE_ONLY = 1
        const val CACHE_FIRST = 2
        const val NET_ONLY = 3
        const val NET_CACHE = 4
    }
    
    private val headers = hashMapOf<String, String>()
    private val params = hashMapOf<String, Any>()
    var cacheKey: String = ""
    
    fun addHeader(key: String, value: String): R {
        headers[key] = value
        return this as R
    }
    
    fun addParam(key: String, value: Any): R {
        val field = value::class.java.getDeclaredField("TYPE")
        val clazz: Class<*> = field.get(null) as Class<*>
        if (clazz.isPrimitive) {
            params[key] = value
        }
        return this as R
    }
    
    fun excute() {
    
    }
    
    fun excute(callback: JsonCallback<T>) {
    
    }
    
    private fun getCall() {
        val builder = Request.Builder()
        for ((key, value) in headers) {
            builder.addHeader(key, value)
        }
        builder.build()
    }
    
    @IntDef(value = [CACHE_ONLY, CACHE_FIRST, NET_ONLY, NET_CACHE])
    annotation class CacheStrategy
}
