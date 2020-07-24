package com.xkf.applicationtest

import android.app.Application

/**
 * @author xiakaifa
 * 功能描述:
 * 时 间： 2020/7/24 16:46
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.init(this)
        ProcessLifecycleOwner.lifecycle.addObserver(ApplicationLifecycleObserve())
    }
}