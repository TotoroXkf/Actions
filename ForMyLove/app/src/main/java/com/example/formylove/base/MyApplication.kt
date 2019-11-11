package com.example.formylove.base

import android.app.Application
import com.example.formylove.utils.ToastUtil

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(this)
    }
}