package com.example.formylove.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.formylove.BaseActivity
import com.example.formylove.R
import com.example.formylove.utils.loadNetWorkImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception


class MainActivity : BaseActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    
    override fun initViewModel() {
        viewModel.headImageLiveData.observe(this, Observer { value ->
            imageHead.loadNetWorkImage(value)
        })
    }
    
    override fun initViews() {
        window.statusBarColor = Color.TRANSPARENT
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = MainAdapter()
        
        tvSubTitle.text = viewModel.subTitle
        viewModel.loadHeadImage()
    }
    
    override fun onResume() {
        super.onResume()
        viewModel.loadHeadImage()
    }
    
    override fun getLayoutId(): Int = R.layout.activity_main
}
