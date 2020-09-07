package com.xkf.databindingtest

import androidx.lifecycle.ViewModel
import java.util.*

class NoDataBindingViewModel : ViewModel() {
    var text = getRandomString()
    var times = 0
        set(value) {
            field = value
            changeColor()
        }
    var addButtonColor = "#123456"
    var deleteButtonColor = "#246810"
    var changeTextButtonColor = "#6200EE"
    var content = ""

    fun onAdd() {
        times++
        changeColor()
        createContent()
    }

    fun onDelete() {
        if (times == 0) {
            return
        }
        times--
        changeColor()
        createContent()
    }

    fun onChangeText() {
        times = 0
        changeColor()
        content = ""
        text = getRandomString()
    }

    private fun changeColor() {
        when {
            times in 0 until 4 -> {
                addButtonColor = "#123456"
                deleteButtonColor = "#246810"
            }
            times in 4 until 7 -> {
                addButtonColor = "#39c5bb"
                deleteButtonColor = "#66ccff"
            }
            times > 7 -> {
                addButtonColor = "#ff0000"
                deleteButtonColor = "#00ff00"
            }
        }
    }

    private fun createContent() {
        val stringBuilder = StringBuilder()
        for (i in 0 until times) {
            stringBuilder.append(text)
        }
        content = stringBuilder.toString()
    }

    private fun getRandomString(): String {
        val str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val random = Random()
        val sb = StringBuffer()
        for (i in 0 until 8) {
            val number: Int = random.nextInt(62)
            sb.append(str[number])
        }
        return sb.toString()
    }
}   