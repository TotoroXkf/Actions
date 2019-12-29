package com.example.formylove.main

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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
        
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getImageByTime()
        }
    }
    
    override fun getLayoutId(): Int = R.layout.activity_main
    
    override fun needDoubleBackExit(): Boolean {
        return true
    }
}
