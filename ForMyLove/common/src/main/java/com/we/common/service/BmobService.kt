package com.we.common.service

import com.we.common.entity.MainPageResponse
import retrofit2.Call
import retrofit2.http.GET

interface BmobService {
    @GET("/1/classes/MainPage")
    fun getMainPageData(): Call<MainPageResponse>
}