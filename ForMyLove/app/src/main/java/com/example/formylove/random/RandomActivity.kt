package com.example.formylove.random

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import kotlinx.android.synthetic.main.activity_random.*

class RandomActivity : BaseActivity() {
    private val viewModel: RandomViewModel by lazy {
        ViewModelProviders.of(this).get(RandomViewModel::class.java)
    }
    private val adapter: RandomListAdapter by lazy {
        RandomListAdapter(viewModel)
    }
    
    override fun getLayoutId(): Int = R.layout.activity_random
    
    override fun initViews() {
        setStatusBarWhite()
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
        
    }
}
