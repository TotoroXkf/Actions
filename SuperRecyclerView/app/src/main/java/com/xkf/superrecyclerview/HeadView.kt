package com.xkf.superrecyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.layout_head.view.*

/**
 * author : xiakaifa
 * 2020/3/10
 */
class HeadView : ConstraintLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onFinishInflate() {
        super.onFinishInflate()

        text.text = "哈哈哈哈"
    }
}