package com.we.common.component

import com.we.common.service.BmobService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitManager {
    val bmobService: BmobService

    init {
        val bmobClient = OkHttpClient.Builder()
            .addNetworkInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val newRequest = request.newBuilder()
                        .addHeader("X-Bmob-Application-Id", "50c056c5958ea03ed4f405efb96c4644")
                        .addHeader("X-Bmob-REST-API-Key", "56e57ac0dd850930a4fd25ca23d21ae7")
                        .build()
                    return chain.proceed(newRequest)
                }
            }).build()
        val bmobRetrofit = Retrofit.Builder()
            .baseUrl("https://api2.bmob.cn/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(bmobClient)
            .build()
        bmobService = bmobRetrofit.create(BmobService::class.java)
    }
}