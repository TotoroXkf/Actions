package com.we.splash

import androidx.lifecycle.ViewModel
import com.we.common.component.CommonUtils

class SplashViewModel : ViewModel() {
    var days: String = "0"

    init {
        days = CommonUtils.getStayDays().toString()
    }
}