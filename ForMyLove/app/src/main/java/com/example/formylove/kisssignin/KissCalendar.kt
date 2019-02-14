package com.example.formylove.kisssignin

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

class KissCalendar : View {
	constructor(context: Context?) : super(context)
	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
	
	init {
		val dm = resources.displayMetrics
		Log.e("xkf123456789", "" + dm.widthPixels)
		Log.e("xkf123456789", "" + dm.heightPixels)
	}
}