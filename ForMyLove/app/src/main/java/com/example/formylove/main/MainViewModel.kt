package com.example.formylove.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formylove.utils.computeDays
import com.example.formylove.utils.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mainModel: MainModel = MainModel()
    
    val subTitle: String
        get() = "今天是我们在一起的" + computeDays().toString() + "天"
    
    val headImageLiveData: MutableLiveData<String> = MutableLiveData()
    
    fun loadHeadImage() {
        GlobalScope.launch {
            val imageUrl = mainModel.getImageUrlByTime()
            log(imageUrl)
            headImageLiveData.postValue(imageUrl)
        }
    }
}