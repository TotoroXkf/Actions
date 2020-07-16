package com.xkf.databingdingtest

import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    var isChecked = MutableLiveData(false)
    var checkText = MutableLiveData("当前的选中状态为 ${isChecked.value}")
    var title = MutableLiveData("这是一个大标题")
    var titleColor = MutableLiveData(Color.BLACK)
    var titleSize = MutableLiveData(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            17f,
            Resources.getSystem().displayMetrics
        )
    )
    var subTitle = MutableLiveData("这是一个标题")
    var subTitleColor = MutableLiveData(Color.DKGRAY)
    var subTitleSize = MutableLiveData(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            14f,
            Resources.getSystem().displayMetrics
        )
    )

    fun onCheckedChange(isChecked: Boolean) {
        checkText.value = "当前的选中状态为 ${this.isChecked.value}"
        if (isChecked) {
            titleColor.value = Color.BLUE
            titleSize.value = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                20f,
                Resources.getSystem().displayMetrics
            )

            subTitleColor.value = Color.GREEN
            subTitleSize.value = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                17f,
                Resources.getSystem().displayMetrics
            )
        } else {
            titleColor.value = Color.BLACK
            titleSize.value = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                20f,
                Resources.getSystem().displayMetrics
            )

            subTitleColor.value = Color.DKGRAY
            subTitleSize.value = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                14f,
                Resources.getSystem().displayMetrics
            )
        }
    }
}