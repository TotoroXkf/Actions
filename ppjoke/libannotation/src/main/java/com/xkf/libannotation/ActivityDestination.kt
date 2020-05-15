package com.xkf.libannotation

@Target(AnnotationTarget.TYPE)
annotation class ActivityDestination(
    val pageUrl: String,
    val needLogin: Boolean = false,
    val asStarter: Boolean = false
)