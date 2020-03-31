package com.we.formylove.viewmodel

import androidx.lifecycle.ViewModel
import com.we.common.component.CommonConfig
import com.we.common.component.CommonUtils

class MainViewModel : ViewModel() {
    val actionBarTitle = "我们的APP"
    val dayText = "我们在一起的 " + CommonUtils.getStayDays().toString() + " 天"
    val tabNameList = CommonConfig.tabNameList
}