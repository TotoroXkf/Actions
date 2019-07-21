package com.example.coroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            textView.text = ""

            val client = OkHttpClient()
            val request = Request.Builder().url("https://api.github.com/users/totoroXkf").build()
            val result = async(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                response.body!!.string()
            }
            textView.text = result.await()
        }
    }
}
