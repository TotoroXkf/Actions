package formylove.utils

import formylove.base.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {
    private val bmobRetrofit: Retrofit
    private val bmobService: BmobApi
    
    private val githubRetrofit: Retrofit
    private val githubService: GithubApi
    
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
        
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Basic MTEzMTIyNTEzM0BxcS5jb206OTUwNjI4eGtmKg==")
                    .build()
                return chain.proceed(newRequest)
            }
            
        })
        githubRetrofit = Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
        githubService = githubRetrofit.create(GithubApi::class.java)
    }
    
    fun getBmobService() = bmobService
    
    fun getGithubService() = githubService
}
