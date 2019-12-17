package com.example.formylove.base

import android.R
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.formylove.utils.HandlerHelper
import com.example.formylove.utils.toast

abstract class BaseActivity : AppCompatActivity() {
    private var canExit = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViewModel()
        initViews()
    }
    
    abstract fun getLayoutId(): Int
    
    protected open fun initViews() {}
    
    protected open fun initViewModel() {}
    
    override fun onBackPressed() {
        if (!needDoubleBackExit()) {
            super.onBackPressed()
            return
        }
        
        if (canExit) {
            super.onBackPressed()
            return
        }
        
        canExit = true
        toast("再次点击退出应用~~~")
        HandlerHelper.postDelay(1500) {
            canExit = false
        }
    }
    
    protected fun setStatusBarWhite() {
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    
    protected open fun needDoubleBackExit() = false
    
    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    open fun hideKeyboard() {
        val v = findViewById<View>(R.id.content)
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (v != null && im != null) {
            im.hideSoftInputFromWindow(
                v.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    
    /**
     * 弹起软键盘
     */
    open fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.toggleSoftInput(0, InputMethodManager.SHOW_FORCED)
    }
}