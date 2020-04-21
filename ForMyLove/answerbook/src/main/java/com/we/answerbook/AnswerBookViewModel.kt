package com.we.answerbook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnswerBookViewModel : ViewModel() {
    val isLoading = MutableLiveData(false)

    var hintText = "有什么问题要问喵喵喵宝呀？"
    val buttonText = MutableLiveData("确认")

    var cardIsShowBack = false
}
