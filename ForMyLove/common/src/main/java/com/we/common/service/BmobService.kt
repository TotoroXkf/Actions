package com.we.common.service

import com.we.common.entity.LoveStatement
import com.we.common.entity.LoveStatementResponse
import com.we.common.entity.MainPageResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BmobService {
    /**
     * 首页数据
     */
    @GET("/1/classes/MainPage")
    fun getMainPageData(): Call<MainPageResponse>

    /**
     * 获取所有的恋爱语句
     */
    @GET("/1/classes/Statement")
    fun getLoveStatementData(): Call<LoveStatementResponse>

    /**
     * 增加新的恋爱语句
     */
    @POST("/1/classes/Statement")
    fun addNewStatement(@Body statement: LoveStatement): Call<ResponseBody>

    /**
     * 删除恋爱语句
     */
    @DELETE("/1/classes/Statement/{objectId}")
    fun deleteStatement(@Path("objectId") objectId: String): Call<ResponseBody>

    /**
     * 修改恋爱语句
     */
    @PUT(("/1/classes/Statement/{objectId}"))
    fun updateStatement(
        @Path("objectId") objectId: String,
        @Body statement: LoveStatement
    ): Call<ResponseBody>
}