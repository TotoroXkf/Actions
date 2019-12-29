package com.example.formylove.base

import android.app.Application
import com.example.formylove.utils.ToastHelper

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ToastHelper.init(this)
    }
}