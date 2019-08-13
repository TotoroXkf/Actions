package com.example.coroutinestest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            textView.text = ""
            progressBar.visibility = View.VISIBLE

            val result = loadData()

            progressBar.visibility = View.GONE
            textView.text = result
        }


    }

    private suspend fun loadData():String = withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://api.github.com/users/totoroXkf").build()
        val response = client.newCall(request).execute()
        response.body!!.string()
    }

//    private suspend fun loadData():String = async(Dispatchers.IO) {
//        val client = OkHttpClient()
//        val request = Request.Builder().url("https://api.github.com/users/totoroXkf").build()
//        val response = client.newCall(request).execute()
//        response.body!!.string()
//    }.await()

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}
