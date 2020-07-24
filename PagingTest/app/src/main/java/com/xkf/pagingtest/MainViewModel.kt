package com.xkf.pagingtest

import android.annotation.SuppressLint
import android.util.Log
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

@SuppressLint("RestrictedApi")
class MainViewModel : ViewModel() {
    val pageLiveData: LiveData<PagedList<String>>

    val cacheLiveData: MutableLiveData<PagedList<String>>
    lateinit var dataSource: DataSource<Int, String>

    // Factory 用于创建DataSource
    inner class MainDataSourceFactory : DataSource.Factory<Int, String>() {
        override fun create(): DataSource<Int, String> {
            // 这里每次移都生成新的DataSource
            dataSource = MainDataSource()
            return dataSource
        }
    }

    // DataSource 用于真正的加载数据
    class MainDataSource : ItemKeyedDataSource<Int, String>() {
        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<String>) {
            // 延时一秒之后填充 0-9 的一个list
            Thread.sleep(1000)
            val list = arrayListOf<String>()
            for (i in 0 until 10) {
                list.add(i.toString())
            }
            callback.onResult(list)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<String>) {
            // 延迟一秒之后填充后续的10个数字成为一个list
            Thread.sleep(1000)
            if (params.key < 49) {
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

    private val boundaryCallback = object : PagedList.BoundaryCallback<String>() {
        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()

            Log.e("xkf", "第一页加载为空时回调")
        }

        override fun onItemAtEndLoaded(itemAtEnd: String) {
            super.onItemAtEndLoaded(itemAtEnd)

            Log.e("xkf", "分页完全结束时回调，传入的参数是最后一个item $itemAtEnd")
        }

        override fun onItemAtFrontLoaded(itemAtFront: String) {
            super.onItemAtFrontLoaded(itemAtFront)

            Log.e("xkf", "第一页加载完成时回调，传入的参数是第一个item $itemAtFront")
        }
    }

    init {
        // 生成Paging的LiveData
        val config = PagedList.Config.Builder()
            .setPageSize(10) // 页长度
            .setPrefetchDistance(0) // 距离多远开始加载
            .build()
        pageLiveData = LivePagedListBuilder(MainDataSourceFactory(), config)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // 缓存使用
        val pageList = PagedList.Builder<Int, String>(CacheDataSource(), config)
            .setFetchExecutor(ArchTaskExecutor.getIOThreadExecutor())
            .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor())
            .build()
        cacheLiveData = MutableLiveData()
        cacheLiveData.postValue(pageList)
    }
}