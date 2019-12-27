package com.example.class18

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API {
    @GET("/")
    fun getInfo(): Single<GitHubInfo>
}

fun getInfo() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(API::class.java)
    api.getInfo()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : SingleObserver<GitHubInfo> {
            override fun onSubscribe(d: Disposable) {
            
            }
            
            override fun onSuccess(info: GitHubInfo) {
                // ....
            }
            
            override fun onError(e: Throwable) {
            }
        })
}

data class GitHubInfo(
    val authorizations_url: String,
    val code_search_url: String,
    val commit_search_url: String,
    val current_user_authorizations_html_url: String,
    val current_user_repositories_url: String,
    val current_user_url: String,
    val emails_url: String,
    val emojis_url: String,
    val events_url: String,
    val feeds_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val hub_url: String,
    val issue_search_url: String,
    val issues_url: String,
    val keys_url: String,
    val label_search_url: String,
    val notifications_url: String,
    val organization_repositories_url: String,
    val organization_url: String,
    val public_gists_url: String,
    val rate_limit_url: String,
    val repository_search_url: String,
    val repository_url: String,
    val starred_gists_url: String,
    val starred_url: String,
    val team_url: String,
    val user_organizations_url: String,
    val user_repositories_url: String,
    val user_search_url: String,
    val user_url: String
)