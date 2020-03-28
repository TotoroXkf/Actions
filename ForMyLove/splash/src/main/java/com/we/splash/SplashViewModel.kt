package com.we.splash

import androidx.lifecycle.ViewModel
import java.util.*

class SplashViewModel : ViewModel() {
    var days: String = "0"

    init {
        days = computeDays().toString()
    }

    private fun computeDays(): Int {
        val start = Calendar.getInstance()
        start.apply {
            set(Calendar.YEAR, 2018)
            set(Calendar.MONTH, Calendar.JULY)
            set(Calendar.DAY_OF_MONTH, 30)
        }
        val now = Calendar.getInstance()
        val result = (now.time.time - start.time.time) / 1000 / 60 / 60 / 24
        return result.toInt() + 1
    }
}