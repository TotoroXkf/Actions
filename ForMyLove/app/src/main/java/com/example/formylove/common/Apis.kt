package com.example.formylove.common

import com.example.formylove.main.MainActivity
import retrofit2.Call
import retrofit2.http.GET

interface BmobApi {
    @GET("1/classes/test")
    fun test(): Call<MainActivity.Test>
}