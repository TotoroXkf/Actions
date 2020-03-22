package com.xkf.core

/**
 * author : xiakaifa
 * 2020/3/22
 */
data class SubTask(
    val name: String = "",
    val desc: String = "",
    var parent: Task? = null
)