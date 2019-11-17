package com.example.formylove.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formylove.R
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
    
    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
    }
    
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
    
    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.fade_in,
            R.anim.fade_out
        )
    }
}