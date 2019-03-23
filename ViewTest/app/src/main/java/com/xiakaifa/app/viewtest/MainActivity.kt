package com.xiakaifa.app.viewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val view = layoutInflater.inflate(R.layout.activity_main,null)
        setContentView(view)
        (view as MyView).doSome()
    }
}
