package com.example.formylove.base

import com.example.formylove.statement.StatementEntity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

const val APPLICATION_ID = "50c056c5958ea03ed4f405efb96c4644"
const val REST_API_KEY = "56e57ac0dd850930a4fd25ca23d21ae7"
const val BMOB_BASE_URL = "https://api2.bmob.cn/"

const val GITHUB_BASE_URL = "https://api.github.com/"

interface BmobApi {
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
    
    @DELETE("/1/classes/Statement/{objectId}")
    fun deleteStatement(@Path("objectId") objectId: String): Call<ResponseBody>
}

interface GithubApi {
    @GET("/")
    fun getFileContent(@Url url: String): Call<GithubContentData>
    
    @GET("repos/TotoroXkf/ForMyLoveData/contents/HeadImage/data.json")
    fun getHeadImages(): Call<GithubContentData>
}