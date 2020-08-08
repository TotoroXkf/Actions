package com.xkf.trainingplatform.base

import android.app.Application


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Global.init(this)
    }
}