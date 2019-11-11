package com.example.formylove.utils

import android.util.Log

const val TAG = "xiakaifa: "

fun log(message: String) {
    Log.e(TAG, message)
}

fun log(message: Int) {
    Log.e(TAG, message.toString())
}