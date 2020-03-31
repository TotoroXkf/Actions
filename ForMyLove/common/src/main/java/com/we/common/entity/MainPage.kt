package com.we.common.entity


data class MainPageResponse(
    val results: List<MainPage>
)

data class MainPage(
    val title: String,
    val desc: String,
    val imageUrl: String
)