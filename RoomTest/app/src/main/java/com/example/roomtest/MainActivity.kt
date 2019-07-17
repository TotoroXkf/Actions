package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, MyDatabase::class.java, "my_database").build()
        val myDao = db.getBookDao()
        var liveData: LiveData<List<Book>>
        Thread {
            //            myDao.insertBook(Book(id = 0, name = "First Book", page = 500, author = "xkf"))
//            myDao.insertBook(Book(id = 0, name = "Second Book", page = 520, author = "xkf"))
//            myDao.updateBook(Book(id = 2, name = "ex Book", page = 500, author = "xkf"))
//            myDao.deleteBook(Book(id = 2, name = "ex Book", page = 500, author = "xkf"))
//            val dataList = myDao.getAllBooks()
//            val book = myDao.getBookById(1)
//            val list = myDao.getBookByColumnName()

            liveData = myDao.getAllBooks()
            runOnUiThread {
                liveData.observe(MainActivity@ this, Observer {
                    Log.e("xkf", "拿到了新的数据")
                })
            }

            Thread.sleep(3000)

            myDao.updateBook(Book(id = 1, name = "First Book", page = 500, author = "xkf"))
        }.start()

    }
}

