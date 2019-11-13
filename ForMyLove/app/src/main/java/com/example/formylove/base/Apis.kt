package com.example.formylove.base

import com.example.formylove.main.MainActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BmobApi {
    
    fun test():Call<ResponseBody>
}