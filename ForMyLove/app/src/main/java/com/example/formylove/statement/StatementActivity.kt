package com.example.formylove.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import kotlinx.android.synthetic.main.activity_statement.*

class StatementActivity : BaseActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StatementViewModel::class.java)
    }
    
    private val adapter by lazy {
        StatementAdapter(viewModel)
    }
    
    override fun getLayoutId(): Int = R.layout.activity_statement
    
    override fun initViewModel() {
        viewModel.textList.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }
    
    override fun initViews() {
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
    }
}
