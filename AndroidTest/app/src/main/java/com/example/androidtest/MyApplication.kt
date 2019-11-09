package com.example.androidtest

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        CrashHandler.init(this)
    }
}