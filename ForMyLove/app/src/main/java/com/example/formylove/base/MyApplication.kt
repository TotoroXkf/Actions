package com.example.formylove.base

import android.app.Application
import com.example.formylove.utils.ImageLoader
import com.example.formylove.utils.ToastHelper

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(this)
        ToastHelper.init(this)
    }
}