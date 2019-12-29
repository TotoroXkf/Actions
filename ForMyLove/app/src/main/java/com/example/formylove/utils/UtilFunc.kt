package com.example.formylove.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import java.util.*

const val TAG = "xiakaifa: "

/**
 * 计算我们在一起的天数
 */
fun computeDays(): Int {
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

/**
 * 通过view找到Activity
 */
fun View.getActivity(): Activity? {
    var activityContext: Context? = context
    while (activityContext !is Activity && activityContext is ContextWrapper) {
        activityContext = activityContext.baseContext
    }
    
    return activityContext as? Activity
}

/**
 * 通过view拿到ViewModel
 */
fun <T : ViewModel> View.getViewModel(clazz: Class<T>): T? {
    val activity = getActivity()
    activity ?: return null
    if (activity !is FragmentActivity) {
        return null
    }
    return ViewModelProviders.of(getActivity() as FragmentActivity).get(clazz)
}

/**
 * dp -> px
 */
fun dp2px(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)

/**
 * 获取屏幕高度
 */
fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels

/**
 * 获取屏幕宽度
 */
fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

/**
 * base64字符串解码
 */
fun String.decodeBase64(): String {
    return String(Base64.decode(this, Base64.DEFAULT))
}

/**
 * log
 */
fun log(message: String) {
    Log.e(TAG, message)
}

/**
 * log
 */
fun log(message: Int) {
    Log.e(TAG, message.toString())
}

/**
 * 通过url获取到图片
 */
fun ImageView.loadNetWorkImage(url: String) {
    Glide.with(this).load(url).into(this)
}

/**
 * 加载bitmap到ImageView
 */
fun ImageView.loadImage(bitmap: Bitmap) {
    Glide.with(this).load(bitmap).into(this)
}

fun parseImage(url: String): Bitmap? {
    val response = RetrofitHelper.getGithubService().getFileContent(url).execute()
    if (response.isSuccessful) {
        val body = response.body()!!
        val byteArray = Base64.decode(body.content, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
    return null
}