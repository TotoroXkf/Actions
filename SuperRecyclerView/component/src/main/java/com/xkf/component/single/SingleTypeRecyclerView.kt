package com.xkf.component.single

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class SingleTypeRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}