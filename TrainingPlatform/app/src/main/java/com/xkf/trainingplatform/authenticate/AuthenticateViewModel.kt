package com.xkf.trainingplatform.authenticate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AuthenticateViewModel : ViewModel() {
    val step1Selected = MutableLiveData(true)
    val step2Selected = MutableLiveData(false)
    val step3Selected = MutableLiveData(false)
}