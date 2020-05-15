package com.xkf.androidtest

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.test.espresso.IdlingResource;

/**
 * author : xiakaifa
 * 2020/5/7
 */
class MainViewModel : ViewModel(), IdlingResource {
    private val handler = Handler(Looper.getMainLooper())
    val liveData = MutableLiveData(mutableListOf<String>())

    @Volatile
    var isIdle = false
    var resCallback: IdlingResource.ResourceCallback? = null

    fun loadData() {
        isIdle = false
        handler.postDelayed(
            {
                liveData.value = mockData()
                isIdle = true
                resCallback?.onTransitionToIdle()
            },
            2000
        )
    }

    private fun mockData(): MutableList<String> {
        val result = mutableListOf<String>()
        for (i in 0 until 100) {
            result.add("Item : $i")
        }
        return result
    }

    override fun getName(): String {
        return javaClass.name
    }

    override fun isIdleNow(): Boolean = isIdle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        resCallback = callback
    }
}