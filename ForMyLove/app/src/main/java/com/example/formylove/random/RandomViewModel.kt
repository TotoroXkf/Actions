package com.example.formylove.random

import androidx.lifecycle.ViewModel

class RandomViewModel : ViewModel() {
    val currentThingsList = mutableListOf<String>()
    private val lastThingsList = mutableListOf<String>()
    
    fun resetThingsList() {
        currentThingsList.clear()
        currentThingsList.addAll(lastThingsList)
        lastThingsList.clear()
    }
}