package com.example.formylove.main

import androidx.lifecycle.ViewModel
import com.example.formylove.utils.computeDays

class MainViewModel : ViewModel() {
    val subTitle:String
        get() = "今天是我们在一起的"+ computeDays().toString()+"天"
}