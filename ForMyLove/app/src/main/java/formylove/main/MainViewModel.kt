package formylove.main

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import formylove.base.HEAD_IMAGE_JSON_URL
import formylove.utils.ImageLoader
import formylove.utils.computeDays
import formylove.utils.getScreenWidth
import formylove.utils.parseGithubContentToObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainViewModel : ViewModel() {
    val subTitle: String
        get() = "今天是我们在一起的" + computeDays().toString() + "天"
    
    suspend fun getImageByTime(): Bitmap {
        val headImages = withContext(Dispatchers.IO) {
            parseGithubContentToObject(HEAD_IMAGE_JSON_URL, HeadImages::class.java)
        }
        
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        var url = ""
        headImages.dataList.forEach { item ->
            if (hour >= item.start && hour <= item.end) {
                url = item.url
            }
        }
        
        val bitmap = withContext(Dispatchers.IO) {
            ImageLoader.getBitmap(url, width = getScreenWidth())
        }
        
        GlobalScope.launch {
            ImageLoader.save(url, bitmap)
        }
        
        return bitmap
    }
}