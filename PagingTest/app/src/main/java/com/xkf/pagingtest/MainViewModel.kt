package com.xkf.pagingtest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class MainViewModel : ViewModel() {
    val pageLiveData: LiveData<PagedList<String>>
    
    // Factory 用于创建DataSource
    class MainDataSourceFactory : DataSource.Factory<Int, String>() {
        override fun create(): DataSource<Int, String> {
            return MainDataSource()
        }
    }
    
    // DataSource 用于真正的加载数据
    class MainDataSource : ItemKeyedDataSource<Int, String>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<String>
        ) {
            Thread.sleep(1000)
            val list = arrayListOf<String>()
            for (i in 0 until 10) {
                list.add(i.toString())
            }
            callback.onResult(list)
        }
        
        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<String>) {
            Thread.sleep(1000)
            if (params.key < 100) {
                val list = arrayListOf<String>()
                for (i in params.key + 1 until params.key + 11) {
                    list.add(i.toString())
                }
                callback.onResult(list)
            } else {
                callback.onResult(emptyList())
            }
        }
        
        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<String>) {
            callback.onResult(emptyList())
        }
        
        // key代表了加载数据的一个参考
        override fun getKey(item: String): Int {
            return item.toInt()
        }
    }
    
    init {
        // 生成Paging的LiveData
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()
        pageLiveData = LivePagedListBuilder(MainDataSourceFactory(), config).build()
    }
}