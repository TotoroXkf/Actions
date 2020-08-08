package com.xkf.trainingplatform.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterDetailViewModel : ViewModel() {
    val postButtonEnable = MutableLiveData<Boolean>(false)
}