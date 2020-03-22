package com.xkf.core

/**
 * author : xiakaifa
 * 2020/3/22
 */
data class Task(
    val name: String = "",
    val desc: String = "",
    val time: String = "",
    var subTask: SubTask? = null
)