package com.xiakaifa.app.viewtest

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MyView :ConstraintLayout{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun doSome(){
        Log.e("xkf","casjkadisndsadas")
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<TextView>(R.id.text).setText("xsadsadsadsadas")
    }
}