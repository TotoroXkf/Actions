package com.example.myapp.weather

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

const val KEY = "b172b66828f44348818e44e94bf76ce1"

lateinit var retrofit: Retrofit
lateinit var weatherService: WeatherService

interface WeatherService {
    @GET("now")
    fun getWeatherNow(@Query("key") key: String, @Query("location") location: String): Call<ResponseBody>
}

fun apiInit() {
    retrofit = Retrofit.Builder()
            .baseUrl("https://free-api.heweather.com/s6/weather/")
            .build()
    weatherService = retrofit.create(WeatherService::class.java)
}