package com.example.formylove.utils

import android.annotation.SuppressLint
import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnakeBarHelper {
    var snakeBar: Snackbar? = null
    
    @SuppressLint("WrongConstant")
    fun showSnakeBar(view: View, text: String, isPersist: Boolean = false) {
        if (snakeBar == null) {
            snakeBar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        }
        snakeBar?.let {
            it.setText(text)
            if (isPersist) {
                it.duration = Snackbar.LENGTH_INDEFINITE
            } else {
                it.duration = Snackbar.LENGTH_SHORT
            }
            it.show()
        }
    }
    
    fun hideSnakeBar() {
        snakeBar?.dismiss()
    }
}