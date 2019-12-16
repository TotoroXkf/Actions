package com.example.formylove.base

import android.os.Bundle
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
    
    protected open fun needDoubleBackExit() = false
}