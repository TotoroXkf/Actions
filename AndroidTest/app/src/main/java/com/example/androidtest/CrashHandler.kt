package com.example.androidtest

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import android.os.Process
import android.util.Log
import java.io.File
import java.lang.Exception

@SuppressLint("StaticFieldLeak")
object CrashHandler : Thread.UncaughtExceptionHandler {
    private const val TAG = "xiakaifa"
    private const val DEBUG = true
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
            // 有系统提供的，就使用系统的
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

        val dir = File(Environment.getExternalStorageState())
    }

    private fun uploadExceptionToServer(e: Throwable) {
        // nothing
    }
}