package com.example.formylove.base

import com.example.formylove.main.HeadImageEntity
import com.example.formylove.statement.StatementEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BmobApi {
    /**
     * 主页head图片
     */
    @GET("/1/classes/HeadImage")
    fun getHeadImages(): Call<HeadImageEntity>
    
    /**
     * 获取恋爱语句
     */
    @GET("/1/classes/Statement")
    fun getStatements(): Call<StatementEntity>
    
    /**
     *  上传新的恋爱语句
     */
    @POST("/1/classes/Statement")
    fun uploadStatement(@Body data: StatementEntity.StatementData): Call<ResponseBody>
}