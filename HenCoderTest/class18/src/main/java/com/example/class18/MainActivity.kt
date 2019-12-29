package com.example.class18

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel = ViewModel()
    
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViewModel()
    }
    
    private fun initViewModel() {
        viewModel.textObserver = Consumer {
            textView.text = it
        }
        
        viewModel.getInfo()
    }
}
