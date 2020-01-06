package formylove.random

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.formylove.R
import formylove.base.BaseActivity
import formylove.base.KeyBoardEvent
import formylove.utils.KeyboardHelper
import kotlinx.android.synthetic.main.activity_random.*
import kotlinx.android.synthetic.main.activity_random.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class RandomActivity : BaseActivity() {
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
        
        })
        
        viewModel.deleteLiveData.observe(this, Observer {
        
        })
        
        viewModel.resetLiveData.observe(this, Observer {
        
        })
    }
    
    override fun initViews() {
        setStatusBarWhite()
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
