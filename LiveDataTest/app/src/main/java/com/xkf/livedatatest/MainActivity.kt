package com.xkf.livedatatest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe

class MainActivity : AppCompatActivity() {
    private val liveData = MutableLiveData<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myLiveData = MyLiveData()
        myLiveData.observe(this) {

        }
        myLiveData.observe(this) {

        }
    }

    class MyLiveData : LiveData<String>() {
        override fun onActive() {
            super.onActive()

            Log.e("大龙猫", "onActive: ")
        }

        override fun onInactive() {
            super.onInactive()

            Log.e("大龙猫", "onInactive: ")
        }
    }
}