package com.example.formylove.statement

import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import com.example.formylove.utils.hideSnakeBar
import com.example.formylove.utils.showSnakeBar
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
    
    private var fabIsHide = false
    private val fabAnimation = ObjectAnimator()
    
    override fun getLayoutId(): Int = R.layout.activity_statement
    
    override fun initViewModel() {}
    
    override fun initViews() {
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        
        fabAnimation.setPropertyName("y")
        fabAnimation.target = floatingActionButton
        floatingActionButton.setOnClickListener {
            handleInputDialog()
        }
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                handleFab(dy)
            }
        })
        val itemTouchHelper = ItemTouchHelper(DeleteTouchCallback())
        itemTouchHelper.attachToRecyclerView(recycleView)
        
        refreshLayout.setOnRefreshListener {
            refresh()
        }
        
        refresh()
    }
    
    private fun handleInputDialog() {
        val view = recycleView
        MaterialDialog(this).show {
            input { _, text ->
                showSnakeBar(view, "正在上传新的语句", true)
                launch(Dispatchers.Main) {
                    viewModel.uploadNewStatement(text.toString())
                    hideSnakeBar()
                }
            }
            title(text = "输入新的语句")
            negativeButton(text = "爽宝再想想")
            positiveButton(text = "确定啦~~")
        }
    }
    
    private fun refresh() = launch(Dispatchers.Main) {
        refreshLayout.isRefreshing = true
        viewModel.loadStatements()
        adapter.notifyDataSetChanged()
        refreshLayout.isRefreshing = false
    }
    
    private fun handleFab(dy: Int) {
        // 向上滑动隐藏悬浮按钮，反之显示
        if (fabAnimation.isRunning) {
            return
        }
        if (fabIsHide && dy < -5) {
            fabIsHide = false
            fabAnimation.reverse()
        } else if (!fabIsHide && dy > 5) {
            fabIsHide = true
            fabAnimation.setFloatValues(
                floatingActionButton.y,
                floatingActionButton.y + 500f
            )
            fabAnimation.start()
        }
    }
    
    inner class DeleteTouchCallback : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.RIGHT,
        ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }
        
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (direction != ItemTouchHelper.LEFT && direction != ItemTouchHelper.RIGHT) {
                return
            }
            val position = viewHolder.adapterPosition
            viewModel.deleteStatement(position)
            adapter.notifyItemRemoved(position)
        }
    
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}
