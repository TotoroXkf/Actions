package com.xkf.pagingtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList


class MyViewModel : ViewModel() {
    val pageList: LiveData<PagedList<MyModel>>
    val endLiveData = MutableLiveData(false)

    init {
        // pageList的配置
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()
        val dataSourceFactory = DataSourceFactory(endLiveData)
        pageList = LivePagedListBuilder(dataSourceFactory, config).build()
    }
}