package com.example.formylove.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import com.jakewharton.disklrucache.DiskLruCache
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.math.roundToInt


object ImageLoader {
    private lateinit var lruCache: LruCache<String, Bitmap>
    private lateinit var diskLruCache: DiskLruCache
    
    fun init(context: Context) {
        val maxMemory = Runtime.getRuntime().maxMemory().toInt()
        lruCache = object : LruCache<String, Bitmap>(maxMemory / 8) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return value.byteCount / 1024
            }
        }
        
        val cacheFile = getDiskCacheDir(context)
        val version = getAppVersion(context)
        val diskCacheSize = 150 * 1024 * 1024
        diskLruCache = DiskLruCache.open(cacheFile, version, 1, diskCacheSize.toLong())
    }
    
    /**
     * 获取图片，并且保存到缓存。外部只需要调用这一个方法就好
     */
    fun getBitmap(url: String, width: Int = -1, height: Int = -1): Bitmap {
        val urlMD5 = hashKeyForDisk(url)
        var bitmap = getFromCache(urlMD5)
        if (bitmap != null) {
            return bitmap
        }
        bitmap = getFromNetWork(url)
        return compressBitmap(bitmap, width, height)
    }
    
    private fun findMemory(urlMD5: String): Bitmap? {
        return lruCache.get(urlMD5)
    }
    
    /**
     * 从硬盘中查找
     */
    private fun findDisk(urlMD5: String): Bitmap? {
        val snapshot = diskLruCache.get(urlMD5) ?: return null
        val inputStream = snapshot.getInputStream(0)
        return BitmapFactory.decodeStream(inputStream)
    }
    
    /**
     * 从缓存中查找，包括内存和硬盘
     */
    private fun getFromCache(urlMD5: String): Bitmap? {
        var bitmap: Bitmap? = findMemory(urlMD5)
        if (bitmap != null) {
            return bitmap
        }
        
        bitmap = findDisk(urlMD5)
        if (bitmap != null) {
            return bitmap
        }
        return null
    }
    
    /**
     * 从网络获取数据
     */
    private fun getFromNetWork(url: String): Bitmap {
        return parseGithubContentToBitmap(url)
    }
    
    /**
     * 保存图片到缓存，包括内存和硬盘。
     */
    fun save(url: String, bitmap: Bitmap) {
        val urlMD5 = hashKeyForDisk(url)
        saveToMemory(urlMD5, bitmap)
        val byteArray = bitmap2Bytes(bitmap)
        saveToDisk(urlMD5, byteArray)
    }
    
    /**
     * 保存图片的内存
     */
    private fun saveToMemory(urlMD5: String, bitmap: Bitmap) {
        lruCache.put(urlMD5, bitmap)
    }
    
    /**
     * 保存图片到硬盘
     */
    private fun saveToDisk(urlMD5: String, bytes: ByteArray) {
        val editor = diskLruCache.edit(urlMD5) ?: return
        try {
            val outputStream = editor.newOutputStream(0)
            outputStream.write(bytes)
            outputStream.flush()
            editor.commit()
        } catch (e: IOException) {
            editor.abort()
        }
    }
    
    /**
     * 按照采样率压缩图片
     */
    private fun compressBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        val bytes = bitmap2Bytes(bitmap)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
        options.inSampleSize = calculateInSampleSize(options, width, height)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
    }
    
    /**
     * 根据宽高计算采样率
     */
    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        when {
            reqWidth != -1 && reqHeight != -1 -> {
                if (height > reqHeight || width > reqWidth) {
                    val heightRatio = (height.toFloat() / reqHeight.toFloat()).roundToInt()
                    val widthRatio = (width.toFloat() / reqWidth.toFloat()).roundToInt()
                    inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
                }
            }
            reqWidth != -1 -> {
                if (width > reqWidth) {
                    val widthRatio = (width.toFloat() / reqWidth.toFloat()).roundToInt()
                    inSampleSize = widthRatio
                }
            }
            reqHeight != -1 -> {
                if (height > reqHeight) {
                    val heightRatio = (height.toFloat() / reqHeight.toFloat()).roundToInt()
                    inSampleSize = heightRatio
                }
            }
        }
        return inSampleSize
    }
    
    /**
     * 获取硬盘缓存的文件夹目录
     */
    private fun getDiskCacheDir(context: Context): File {
        val cachePath = context.cacheDir.path
        return File(cachePath + File.separator.toString() + "bitmap")
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
    
    /**
     * 对url做md5处理
     */
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
    
    /**
     * byte转换成16进制字符串
     */
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
    
    /**
     * 将 bitmap 转换为 bytes
     */
    private fun bitmap2Bytes(bm: Bitmap): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }
}