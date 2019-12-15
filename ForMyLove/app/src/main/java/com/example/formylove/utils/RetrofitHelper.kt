package com.example.formylove.utils

import com.example.formylove.base.BmobApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


const val APPLICATION_ID = "50c056c5958ea03ed4f405efb96c4644"
const val REST_API_KEY = "56e57ac0dd850930a4fd25ca23d21ae7"
const val BMOB_BASE_URL = "https://api2.bmob.cn/"

object RetrofitHelper {
    private val bmobRetrofit: Retrofit
    private val bmobService: BmobApi
    
    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Bmob-Application-Id", APPLICATION_ID)
                    .addHeader("X-Bmob-REST-API-Key", REST_API_KEY)
                    .build()
                return chain.proceed(newRequest)
            }
            
        })
        val okHttpClient = okHttpClientBuilder.build()
        bmobRetrofit = Retrofit.Builder()
            .baseUrl(BMOB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        bmobService = bmobRetrofit.create(BmobApi::class.java)
    }
    
    fun getBmobService() = bmobService
}
