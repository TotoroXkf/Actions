package com.example.formylove.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object ImageLoader {
    private lateinit var lruCache: LruCache<String, Bitmap>
    private var version = 0
    
    fun init(context: Context) {
        version = getAppVersion(context)
        
        val maxMemory = Runtime.getRuntime().maxMemory().toInt()
        lruCache = object : LruCache<String, Bitmap>(maxMemory / 8) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
    }
    
    suspend fun getBitmap(url: String): Bitmap {
        val urlMD5 = hashKeyForDisk(url)
        var bitmap = getFromCache(hashKeyForDisk(url))
        if (bitmap != null) {
            return bitmap
        }
        bitmap = getFromNetWork(url)
        save(urlMD5, bitmap)
        return bitmap
    }
    
    private fun findMemory(url: String): Bitmap? {
        return lruCache.get(url)
    }
    
    private fun findDisk(url: String): Bitmap? {
        return null
    }
    
    private suspend fun getFromCache(urlMD5: String) = withContext(Dispatchers.IO) {
        var bitmap: Bitmap? = findMemory(urlMD5)
        if (bitmap != null) {
            return@withContext bitmap
        }
        
        bitmap = findDisk(urlMD5)
        if (bitmap != null) {
            return@withContext bitmap
        }
        return@withContext bitmap
    }
    
    private suspend fun getFromNetWork(url: String) = withContext(Dispatchers.IO) {
        return@withContext parseGithubContentToBitmap(url)
    }
    
    private fun save(url: String, bitmap: Bitmap) {
        saveToMemory(url, bitmap)
        saveToDisk(url, bitmap)
    }
    
    private fun saveToMemory(url: String, bitmap: Bitmap) {
        lruCache.put(url, bitmap)
    }
    
    private fun saveToDisk(url: String, bitmap: Bitmap) = GlobalScope.launch {
    
    }
    
    private fun getAppVersion(context: Context): Int {
        try {
            val info = context.packageManager.getPackageInfo(context.packageName, 0)
            return info.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return 1
    }
    
    
    private fun hashKeyForDisk(key: String): String {
        val cacheKey: String
        cacheKey = try {
            val mDigest = MessageDigest.getInstance("MD5")
            mDigest.update(key.toByteArray())
            bytesToHexString(mDigest.digest()) ?: ""
        } catch (e: NoSuchAlgorithmException) {
            key.hashCode().toString()
        }
        return cacheKey
    }
    
    private fun bytesToHexString(bytes: ByteArray): String? {
        val sb = StringBuilder()
        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                sb.append('0')
            }
            sb.append(hex)
        }
        return sb.toString()
    }
}