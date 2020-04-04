package com.we.common.component


interface CommonHandler {
    fun fullScreen()

    fun cancelFullScreen()

    fun showKeyboard()

    fun hideKeyboard()

    fun open(route: String)
}