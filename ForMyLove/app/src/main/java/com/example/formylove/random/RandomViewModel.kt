package com.example.formylove.random

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer

class RandomViewModel : ViewModel() {
    val currentThingsList = mutableListOf<String>()
    private val lastThingsList = mutableListOf<String>()
    
    var isFirstLoadList = true
    
    val addLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<Int>()
    val resetLiveData = MutableLiveData<Boolean>()
    
    fun addNewThing(newThing: String) {
        currentThingsList.add(newThing)
        addLiveData.value = newThing
    }
    
    fun resetThingsList() {
        currentThingsList.clear()
        currentThingsList.addAll(lastThingsList)
        resetLiveData.value = true
    }
    
    fun computeRandom(onFinish: () -> Unit) {
        lastThingsList.clear()
        lastThingsList.addAll(currentThingsList)
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (currentThingsList.size == 1) {
                    onFinish.invoke()
                } else {
                    handler.postDelayed({
                        val index = getNextIndex(currentThingsList.size)
                        currentThingsList.removeAt(index)
                        deleteLiveData.value = 0
                        handler.postDelayed(this, 500)
                    }, 500)
                }
            }
        }
        handler.post(runnable)
    }
    
    private fun getNextIndex(length: Int): Int {
        return 0
    }
}