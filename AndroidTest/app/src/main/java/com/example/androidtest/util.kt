package com.example.androidtest

import android.content.res.Resources
import android.util.TypedValue

fun dp2px(value: Float): Float {
	return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_DIP,
		value,
		Resources.getSystem().displayMetrics
	)
}