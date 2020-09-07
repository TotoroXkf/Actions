package com.xkf.databindingtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class MainViewModel : ViewModel() {
    val title = MutableLiveData("DataBinding")
    val text = MutableLiveData(getRandomString())
    var times = 0
        set(value) {
            field = value
            timesText.value = "x${value}"
            changeColor()
        }
    val timesText = MutableLiveData("x${times}")
    val addButtonColor = MutableLiveData("#123456")
    val deleteButtonColor = MutableLiveData("#246810")
    val changeTextButtonColor = MutableLiveData("#6200EE")
    val content = MutableLiveData("")

    fun onAdd() {
        times++
        createContent()
    }

    fun onDelete() {
        if (times == 0) {
            return
        }
        times--
        createContent()
    }

    fun onChangeText() {
        times = 0
        content.value = ""
        text.value = getRandomString()
    }

    private fun changeColor() {
        when {
            times in 0 until 4 -> {
                addButtonColor.value = "#123456"
                deleteButtonColor.value = "#246810"
            }
            times in 4 until 7 -> {
                addButtonColor.value = "#39c5bb"
                deleteButtonColor.value = "#66ccff"
            }
            times > 7 -> {
                addButtonColor.value = "#ff0000"
                deleteButtonColor.value = "#00ff00"
            }
        }
    }

    private fun createContent() {
        val stringBuilder = StringBuilder()
        for (i in 0 until times) {
            stringBuilder.append(text.value)
        }
        content.value = stringBuilder.toString()
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