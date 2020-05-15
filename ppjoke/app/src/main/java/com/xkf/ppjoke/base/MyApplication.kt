package com.xkf.ppjoke.base

import android.app.Application

/**
 * author : xiakaifa
 * 2020/5/15
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        AppGlobal.setApplication(this)
    }
}