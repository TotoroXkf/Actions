package com.we.common.component

object CommonConfig {
    const val TAB_CHECK_IN = "恋爱打卡"
    const val TAB_LOVE_STATEMENT = "恋爱语句"
    const val TAB_MAKE = "爽宝的制作"

    val tabNameList = listOf(
        TAB_CHECK_IN,
        TAB_LOVE_STATEMENT,
        TAB_MAKE
    )

    val pageDescMap = mapOf(
        TAB_CHECK_IN to "每天都要爱发发哦",
        TAB_LOVE_STATEMENT to "这里有爽宝最喜欢的恋爱语句呢~~",
        TAB_MAKE to "爽宝的精美制作都在这里呢~~"
    )
}