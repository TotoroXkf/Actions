package formylove.random

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import formylove.base.BaseActivity
import formylove.base.KeyBoardEvent
import formylove.utils.KeyboardHelper
import kotlinx.android.synthetic.main.activity_random.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RandomActivity : BaseActivity() {
    private val adapter = ListAdapter()
    
    private val viewModel: RandomViewModel by lazy {
        ViewModelProviders.of(this).get(RandomViewModel::class.java)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        EventBus.getDefault().register(this)
        KeyboardHelper.registerKeyBoardListener(window)
    }
    
    override fun getLayoutId(): Int = R.layout.activity_random
    
    override fun initViewModel() {
        viewModel.addLiveData.observe(this, Observer {
            adapter.setData(viewModel.colorList, viewModel.thingsList)
            turntableView.setColorList(viewModel.colorList)
        })
        
        viewModel.deleteLiveData.observe(this, Observer {
        })
    }
    
    override fun initViews() {
        setStatusBarWhite()
        
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        
        buttonAdd.setOnClickListener {
            //            viewModel.addNewThing("1")
            startActivity(Intent(this, ThingInputActivity::class.java))
        }
        
        buttonTurn.setOnClickListener {
            turntableView.rotate(2000f)
            enableButton(false)
        }
        
        turntableView.addAnimationEndListener {
            enableButton(true)
        }
    }
    
    private fun enableButton(enable: Boolean) {
        buttonAdd.isEnabled = enable
        buttonTurn.isEnabled = enable
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onKeyBoardEvent(event: KeyBoardEvent) {
        val layoutParams = rootLayout.layoutParams
        layoutParams.height = event.viewHeight
        rootLayout.layoutParams = layoutParams
    }
    
    override fun onDestroy() {
        KeyboardHelper.unRegisterKeyBoardListener(window)
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}
