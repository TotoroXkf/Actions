package com.xkf.pagingtest

import androidx.paging.ItemKeyedDataSource

class CacheDataSource : ItemKeyedDataSource<Int, String>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<String>
    ) {
        val list = arrayListOf<String>()
        // 加载缓存数据
        // ......
        for (i in -10 until 0) {
            list.add(i.toString())
        }
        callback.onResult(list)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<String>) {
        callback.onResult(emptyList())
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<String>) {
        callback.onResult(emptyList())
    }

    override fun getKey(item: String): Int {
        return 0
    }
}