package com.we.formylove.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.we.common.component.CommonUtils
import com.we.common.component.RetrofitManager
import com.we.common.entity.MainPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    val actionBarTitle = "我们的APP"
    val dayText = "我们在一起的 " + CommonUtils.getStayDays().toString() + " 天"
    var mainPageList = listOf<MainPage>()

    fun loadMainPageData() = viewModelScope.launch(Dispatchers.Main) {
        val response = withContext(Dispatchers.IO) {
            val response = RetrofitManager.bmobService.getMainPageData().execute()
            if (response.isSuccessful) {
                return@withContext response.body()
            } else {
                return@withContext null
            }
        }
        response?.let {
            mainPageList = it.results
        }
    }
}