package com.example.androidtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.os.Process
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

@SuppressLint("StaticFieldLeak")
object CrashHandler : Thread.UncaughtExceptionHandler {
    private const val TAG = "xiakaifa"
    private var defaultCrashHandler: Thread.UncaughtExceptionHandler? = null
    private lateinit var context: Context
    
    fun init(context: Context) {
        defaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
        this.context = context.applicationContext
    }
    
    override fun uncaughtException(t: Thread, ex: Throwable) {
        try {
            dumpExceptionToSDCard(ex)
            uploadExceptionToServer(ex)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        ex.printStackTrace()
        if (defaultCrashHandler != null) {
            // 有系统提供的，就使用系统的,但是貌似不能正常的退出
            defaultCrashHandler?.uncaughtException(t, ex)
        } else {
            Process.killProcess(Process.myPid())
        }
    }
    
    private fun dumpExceptionToSDCard(e: Throwable) {
        // sd卡无法使用或者不存在
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.e(TAG, "sd卡错误")
            return
        }
        
        var path: String? = Environment.getExternalStorageDirectory()?.path ?: return
        path = "$path/CrashTest/log/"
        val dir = File(path)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val current = System.currentTimeMillis()
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(current))
        val file = File(path + "crash" + "-{$time}" + ".log")
        
        val pw = PrintWriter(BufferedWriter(FileWriter(file)))
        pw.println(time)
        printInfo(pw)
        pw.println()
        e.printStackTrace(pw)
        pw.close()
    }
    
    private fun printInfo(pw: PrintWriter) {
        val packManager = context.packageManager
        val pi = packManager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
        
        // 版本信息
        pw.print("app version: ")
        pw.print(pi.versionName)
        pw.print("_")
        pw.println(pi.longVersionCode)
        
        // Android 信息
        pw.print("OS Version: ")
        pw.print(Build.VERSION.RELEASE)
        pw.print("_")
        pw.println(Build.VERSION.SDK_INT)
        
        // 制造商
        pw.print("vendor : ")
        pw.println(Build.MANUFACTURER)
        
        // 手机型号
        pw.print("Model: ")
        pw.println(Build.MODEL)
        
        // CPU 架构
        pw.print("CPU API: ")
        pw.println(Build.SUPPORTED_ABIS)
    }
    
    private fun uploadExceptionToServer(e: Throwable) {
        // nothing
    }
}