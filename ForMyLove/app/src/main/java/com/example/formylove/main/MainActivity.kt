package com.example.formylove.main

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import com.example.formylove.utils.loadImage
import com.example.formylove.utils.log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class MainActivity : BaseActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    
    override fun initViewModel() {
    }
    
    override fun initViews() {
        window.statusBarColor = Color.TRANSPARENT
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = MainAdapter()
        
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE)
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
        tvSubTitle.text = viewModel.subTitle

        launch(Dispatchers.Main) {
            val bitmap = viewModel.getImageByTime()
            imageHead.loadImage(bitmap)
        }
    }
    
    override fun getLayoutId(): Int = R.layout.activity_main
    
    override fun needDoubleBackExit(): Boolean {
        return true
    }
}
