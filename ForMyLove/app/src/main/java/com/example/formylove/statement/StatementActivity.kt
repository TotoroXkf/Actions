package com.example.formylove.statement

import android.animation.ObjectAnimator
import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import kotlinx.android.synthetic.main.activity_statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        
        floatingActionButton.setOnClickListener {
        
        }
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
        recycleView.addItemDecoration(StatementDivider())
//        val objectAnimator = ObjectAnimator()
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    floatingActionButton.y += 100
//                } else {
//
//                }
            }
        })
        
        refreshLayout.setOnRefreshListener {
            refresh()
        }
        
        refresh()
    }
    
    private fun refresh() = launch(Dispatchers.Main) {
        refreshLayout.isRefreshing = true
        viewModel.loadStatements()
        adapter.notifyDataSetChanged()
        refreshLayout.isRefreshing = false
    }
}
