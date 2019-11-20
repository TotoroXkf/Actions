package com.example.formylove.statement

import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import kotlinx.android.synthetic.main.activity_statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class StatementActivity : BaseActivity(), CoroutineScope by MainScope() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StatementViewModel::class.java)
    }
    
    private val adapter by lazy {
        StatementAdapter(viewModel)
    }
    
    override fun getLayoutId(): Int = R.layout.activity_statement
    
    override fun initViewModel() {}
    
    override fun initViews() {
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.divide_statement_line)!!)
        recycleView.addItemDecoration(dividerItemDecoration)
        
        refreshLayout.setOnRefreshListener {
            refresh()
        }
        
        refresh()
    }
    
    fun refresh() = launch {
        refreshLayout.isRefreshing = true
        viewModel.loadStatements()
        adapter.notifyDataSetChanged()
        refreshLayout.isRefreshing = false
    }
}
