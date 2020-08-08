package com.xkf.trainingplatform.base

import android.app.Activity
import android.content.SharedPreferences


object Global {
    lateinit var application: MyApplication
    lateinit var sharedPreferences: SharedPreferences

    fun init(application: MyApplication) {
        Global.application = application
        sharedPreferences = application.getSharedPreferences("app", Activity.MODE_PRIVATE)
    }
}