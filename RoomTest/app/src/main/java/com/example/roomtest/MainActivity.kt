package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageUrl = "https://i.loli.net/2019/07/17/5d2ee87bdc35879302.jpg"
        val client = OkHttpClient()
        val request = Request.Builder().url(imageUrl).build()
        Thread {
            val db = Room.databaseBuilder(this, MyDatabase::class.java, "my_database").build()
            val imageDao = db.getImageDao()

//            val response = client.newCall(request).execute()
//            if (response.isSuccessful) {
//                val bytes = response.body!!.bytes()
//                val imageEntity = ImageEntity(id = 1, bytes = bytes)
//                imageDao.addImage(imageEntity)
//
//            }
            val imageEntity = imageDao.getImage(1)
            Log.e("xkf", "" + imageEntity.bytes.size)
        }.start()


    }
}

