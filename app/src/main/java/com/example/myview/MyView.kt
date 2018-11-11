package com.example.myview

import android.content.Context
import android.util.AttributeSet
import android.view.View

abstract class MyView : View {


    constructor(context: Context?) : super(context) {
        this.init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        this.init()
    }

    abstract fun init()
}