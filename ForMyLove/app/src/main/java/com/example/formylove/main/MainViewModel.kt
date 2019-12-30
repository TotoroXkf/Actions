package com.example.formylove.main

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.formylove.base.HEAD_IMAGE_JSON_URL
import com.example.formylove.utils.*
import java.util.*

class MainViewModel : ViewModel() {
    val subTitle: String
        get() = "今天是我们在一起的" + computeDays().toString() + "天"
    
    suspend fun getImageByTime(): Bitmap {
        val headImages = parseGithubContentToObject(HEAD_IMAGE_JSON_URL, HeadImages::class.java)
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        var url = ""
        headImages.dataList.forEach { item ->
            if (hour >= item.start && hour <= item.end) {
                url = item.url
            }
        }
        val bitmap = ImageLoader.getBitmap(url)
        return bitmap
    }
}