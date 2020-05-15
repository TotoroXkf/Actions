package com.xkf.ppjoke.mode

/**
 * author : xiakaifa
 * 2020/5/15
 */
data class Destination(
    val asStarter: Boolean,
    val id: Int,
    val isFragment: Boolean,
    val needLogin: Boolean,
    val pageUrl: String
)