package com.example.myview.bezier

import android.content.Context
import android.util.AttributeSet
import com.example.myview.MyView

class BezierView : MyView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun init(context: Context, attrs: AttributeSet) {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    override fun widthWrapContent(widthSize: Int, heightSize: Int) {

    }

    override fun heightWrapContent(heightSize: Int, heightSize1: Int) {

    }
}