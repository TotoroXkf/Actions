package com.example.formylove.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

@SuppressLint("StaticFieldLeak")
object ToastUtil {
    private var context: Context? = null
    
    fun init(context: Context) {
        this.context = context
    }
    
    fun toast(message: String) {
        context ?: return
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun toast(message: String) {
    ToastUtil.toast(message)
}