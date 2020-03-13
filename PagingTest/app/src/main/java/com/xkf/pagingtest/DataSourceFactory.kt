package com.xkf.pagingtest

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import kotlin.random.Random

/**
 * author : xiakaifa
 * 2020/3/12
 */
class DataSourceFactory(private val endLiveData: MutableLiveData<Boolean>) :
    DataSource.Factory<String, MyModel>() {

    override fun create(): DataSource<String, MyModel> {
        return MyDataSource(endLiveData)
    }
}

class MyDataSource(private val endLiveData: MutableLiveData<Boolean>) :
    ItemKeyedDataSource<String, MyModel>() {
    private val random = Random(System.currentTimeMillis())
    private val charString = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGQLMNOPQRSTUVWXYZ"
    private var size = 0

    /**
     * 第一次加载
     * 默认就会调用了
     */
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<MyModel>
    ) {
        Thread.sleep(1500)
        val list = mutableListOf<MyModel>()
        for (i in 0 until 10) {
            var name = ""
            for (i in 0 until 10) {
                name += charString[0.coerceAtLeast(random.nextInt() % charString.length)]
            }
            val model = MyModel(
                name = name,
                id = random.nextLong().toString(),
                finished = random.nextInt() % 2 == 0
            )
            list.add(model)
        }
        size += 10
        callback.onResult(list)
    }

    // 之后的继续加载
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<MyModel>) {
        if (size > 50) {
            endLiveData.postValue(true)
            return
        }
        Thread.sleep(1500)
        val list = mutableListOf<MyModel>()
        for (i in 0 until 10) {
            var name = ""
            for (i in 0 until 10) {
                name += charString[0.coerceAtLeast(random.nextInt() % charString.length)]
            }
            val model = MyModel(
                name = name,
                id = random.nextLong().toString(),
                finished = random.nextInt() % 2 == 0
            )
            list.add(model)
        }
        size += 10
        callback.onResult(list)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<MyModel>) {

    }

    override fun getKey(item: MyModel): String = item.hashCode().toString()
}