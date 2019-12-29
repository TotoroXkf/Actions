package com.example.formylove.main

data class HeadImages(
    val dataList: List<Data>
) {
    data class Data(
        val end: Int,
        val start: Int,
        val url: String
    )
}