package com.example.formylove.main

data class HeadImageEntity(
    val results: List<Result>
) {
    data class Result(
        val endTime: Int,
        val imageUrl: String?,
        val name: String?,
        val startTime: Int
    )
}