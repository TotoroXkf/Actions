package com.example.formylove.main

import com.example.formylove.utils.RetrofitUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class MainModel {
    suspend fun getImageUrlByTime(): String = withContext(Dispatchers.IO) {
        val response = RetrofitUtil.getBmobService().getHeadImages().execute()
        if (response.isSuccessful) {
            val data = response.body() ?: return@withContext ""
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            data.results.forEach { item ->
                if (hour >= item.startTime && hour <= item.endTime) {
                    return@withContext item.imageUrl ?: ""
                }
            }
        }
        return@withContext ""
    }
}