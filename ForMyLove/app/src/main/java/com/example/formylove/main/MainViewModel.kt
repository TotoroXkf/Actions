package com.example.formylove.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.formylove.utils.RetrofitHelper
import com.example.formylove.utils.computeDays
import com.example.formylove.utils.decodeBase64
import com.example.formylove.utils.log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    val subTitle: String
        get() = "今天是我们在一起的" + computeDays().toString() + "天"

//    suspend fun getImageUrlByTime(): String = withContext(Dispatchers.IO) {
//        val response = RetrofitHelper.getBmobService().getHeadImages().execute()
//        if (response.isSuccessful) {
//            val data = response.body() ?: return@withContext ""
//            val calendar = Calendar.getInstance()
//            val hour = calendar.get(Calendar.HOUR_OF_DAY)
//            data.results.forEach { item ->
//                if (hour >= item.startTime && hour <= item.endTime) {
//                    return@withContext item.imageUrl ?: ""
//                }
//            }
//        }
//        return@withContext ""
//    }
    
    suspend fun getImageByTime() = withContext(Dispatchers.IO) {
        val response = RetrofitHelper.getGithubService().getHeadImages().execute()
        val data = response.body()!!
        val json = data.content.decodeBase64()
        val headImages = Gson().fromJson(json, HeadImages::class.java)
    }
}