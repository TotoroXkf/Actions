package com.example.formylove.base

import com.example.formylove.main.HeadImageEntity
import com.example.formylove.main.MainActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BmobApi {
    @GET("/1/classes/HeadImage")
    fun getHeadImages(): Call<HeadImageEntity>
}