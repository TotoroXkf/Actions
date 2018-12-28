package com.example.formylove.picturewall

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Environment
import android.util.LruCache
import com.jakewharton.disklrucache.DiskLruCache
import java.io.BufferedInputStream
import java.io.File
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


private lateinit var diskLruCache: DiskLruCache
private lateinit var lruCache: LruCache<Int, Bitmap>

/*
 * 缓存初始化
 */
fun cacheInit(context: Context) {
    val maxMemory = (Runtime.getRuntime().maxMemory() / 8).toInt()
    val cacheSize = maxMemory / 8
    lruCache = object : LruCache<Int, Bitmap>(cacheSize) {
        override fun sizeOf(key: Int?, value: Bitmap?): Int {
            return value!!.byteCount / 1024
        }
    }
    try {
        val cacheDir = getDiskCacheDir(context, "bitmap")
        if (!cacheDir.exists()) {
            cacheDir.mkdirs()
        }
        diskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 500 * 1024 * 1024)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

/*
 * 添加图片到内存缓存
 */
fun addBitmapToMemoryCache(key: Int, bitmap: Bitmap) {
    if (getBitmapFromMemCache(key) == null) {
        lruCache.put(key, bitmap)
    }
}

/*
 * 从内存缓存读取图片
 */
fun getBitmapFromMemCache(key: Int): Bitmap? {
    return lruCache.get(key)
}

/*
 * 清空内存缓存
 */
fun clearMemoryCache(totalSize: Int) {
    for (i in 0 until totalSize) {
        lruCache.remove(i)
    }
}

/*
 * 写入本地图片缓存，名字为URL转换的MD5值
 */
fun writeToDisk(url: String, bytes: ByteArray) {
    val key = hashKeyForDisk(url)
    val editor = diskLruCache.edit(key)
    if (editor != null) {
        val outStream = editor.newOutputStream(0)
        outStream.write(bytes)
        editor.commit()
    }
}

/*
 * 通过URl查询本地的图片缓存
 */
fun readFromDisk(url: String): ByteArray? {
    val key = hashKeyForDisk(url)
    val snapShot = diskLruCache.get(key)
    if (snapShot != null) {
        val inStream = snapShot.getInputStream(0)
        val bufferedInputStream = BufferedInputStream(inStream)
        return bufferedInputStream.readBytes()
    }
    return null
}


/*
 * 刷新硬盘缓存
 */
fun flushDiskLruCache() {
    diskLruCache.flush()
}

/*
 * 关闭硬盘缓存
 */
fun closeDiskLruCache() {
    diskLruCache.close()
}

/*
 * 获取当前硬盘上的存储位置
 */
private fun getDiskCacheDir(context: Context, uniqueName: String): File {
    val cachePath: String = if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
            || !Environment.isExternalStorageRemovable()) {
        context.externalCacheDir!!.path
    } else {
        context.cacheDir.path
    }
    return File(cachePath + File.separator + uniqueName)
}

/*
 * 获取当前的APP版本
 */
fun getAppVersion(context: Context): Int {
    try {
        val info = context.packageManager.getPackageInfo(context.packageName, 0)
        return info.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return 1
}

/*
 * 通过url生成一个MD5的值
 */
private fun hashKeyForDisk(key: String): String {
    fun bytesToHexString(bytes: ByteArray): String {
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

    val cacheKey: String
    cacheKey = try {
        val mDigest = MessageDigest.getInstance("MD5")
        mDigest.update(key.toByteArray())
        bytesToHexString(mDigest.digest())
    } catch (e: NoSuchAlgorithmException) {
        key.hashCode().toString()
    }

    return cacheKey
}

