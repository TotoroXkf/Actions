package com.example.formylove.random

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import com.example.formylove.base.BaseActivity
import com.example.formylove.base.KeyBoardEvent
import com.example.formylove.utils.KeyboardHelper
import com.example.formylove.utils.log
import kotlinx.android.synthetic.main.activity_random.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RandomActivity : BaseActivity() {
    private val viewModel: RandomViewModel by lazy {
        ViewModelProviders.of(this).get(RandomViewModel::class.java)
    }
    private val adapter: RandomListAdapter by lazy {
        RandomListAdapter(viewModel)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        EventBus.getDefault().register(this)
        KeyboardHelper.registerKeyBoardListener(window)
    }
    
    override fun getLayoutId(): Int = R.layout.activity_random
    
    override fun initViewModel() {
        viewModel.addLiveData.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
        
        viewModel.deleteLiveData.observe(this, Observer {
            recycleView.smoothScrollToPosition(it)
            adapter.notifyDataSetChanged()
        })
        
        viewModel.resetLiveData.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }
    
    override fun initViews() {
        setStatusBarWhite()
        
        recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = adapter
        recycleView.itemAnimator = DefaultItemAnimator()
        val itemTouchHelper = ItemTouchHelper(DeleteTouchCallback(viewModel))
        itemTouchHelper.attachToRecyclerView(recycleView)
        
        operationalAreaView.bind(viewModel)
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onKeyBoardEvent(event: KeyBoardEvent) {
    }
    
    override fun onDestroy() {
        KeyboardHelper.unRegisterKeyBoardListener(window)
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}
