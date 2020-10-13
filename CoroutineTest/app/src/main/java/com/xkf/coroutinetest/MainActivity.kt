package com.xkf.coroutinetest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        normalTest2()
    }

    /**
     * Flow的用法
     */
    private fun flowTest() {
        val flow = flow {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            val user = service.getUser("TotoroXkf")
            emit(user)
        }

        lifecycleScope.launch {
            flow.flowOn(Dispatchers.IO)
                .map { githubUser ->
                    githubUser.name
                }
                .collect { name ->
                    textView.text = name
                }
        }
    }

    /**
     * Retrofit的用法
     */
    private fun retrofitTest() = lifecycleScope.launch {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(UserApi::class.java)
        val user = service.getUser("TotoroXkf")
        val userAwait = service.getUserAwait("TotoroXkf").await()
        textView.text = userAwait.name
    }

    /**
     * 一般异步任务的写法
     */
    private fun normalTest1() = lifecycleScope.launch {
        textView.text = "等待加载"

        val user: GithubUser = withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@withContext service.getUser("TotoroXkf")
        }

        textView.text = user.name
    }

    /**
     * 多异步任务并发的写法
     */
    private fun normalTest2() = lifecycleScope.launch {
        // 挨个定义出要执行的任务
        val task1 = async {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@async service.getUser("TotoroXkf")
        }

        val task2 = async {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@async service.getUser("TotoroXkf")
        }

        val task3 = async {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@async service.getUser("TotoroXkf")
        }

        // 三个任务一起并发
        val list = listOf(task1, task2, task3).awaitAll()

        Log.e("xkf", "name1: " + list[0].name)
        Log.e("xkf", "name2: " + list[1].name)
        Log.e("xkf", "name3: " + list[2].name)
    }

    /**
     * 任务依赖的写法
     */
    private fun normalTest3() = lifecycleScope.launch {
        // 定义出要依赖的任务
        val task1 = async {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@async service.getUser("TotoroXkf")
        }

        val task2 = async {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@async service.getUser("TotoroXkf")
        }
        // 执行前置的任务
        val list = listOf(task1, task2).awaitAll()

        // 将前面拿到的结果放到3里面来使用
        val task3 = async {
            val name1 = list[0].name
            val name2 = list[1].name

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(UserApi::class.java)
            return@async service.getUser(name1 + name2)
        }
        // 获取到最后的结果
        val finalUser = task3.await()
    }
}