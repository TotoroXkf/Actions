package com.xkf.roomtest

import android.app.Application
import androidx.room.Room

/**
 * author : xiakaifa
 * 2020/3/24
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppContext.dataBase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "MyDataBase"
        ).build()
    }
}