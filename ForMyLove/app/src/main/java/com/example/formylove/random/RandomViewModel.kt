package com.example.formylove.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formylove.utils.HandlerHelper
import kotlin.random.Random

class RandomViewModel : ViewModel() {
    val currentThingsList = mutableListOf<String>()
    private val lastThingsList = mutableListOf<String>()
    
    var isFirstLoadList = true
    
    val addLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<Int>()
    val resetLiveData = MutableLiveData<Boolean>()
    
    fun addNewThing(newThing: String) {
        currentThingsList.add(0, newThing)
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
        val runnable = object : Runnable {
            override fun run() {
                if (currentThingsList.size == 1) {
                    onFinish.invoke()
                    return
                }
                
                HandlerHelper.postDelay(500) {
                    val index = getNextIndex(currentThingsList.size)
                    deleteThing(index)
                    HandlerHelper.postDelay(500, this)
                }
            }
        }
        HandlerHelper.post(runnable)
    }
    
    private fun getNextIndex(length: Int): Int {
        val random = Random(System.currentTimeMillis())
        return random.nextInt(1000) % length
    }
    
    fun deleteThing(index: Int) {
        currentThingsList.removeAt(index)
        deleteLiveData.value = index
    }
}