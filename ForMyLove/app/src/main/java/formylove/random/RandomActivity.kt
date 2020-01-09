package formylove.random

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.formylove.R
import formylove.base.AddThingEvent
import formylove.base.BaseActivity
import formylove.utils.KeyboardHelper
import kotlinx.android.synthetic.main.activity_random.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class RandomActivity : BaseActivity() {
    private val adapter = ListAdapter()

    private val viewModel: RandomViewModel by lazy {
        ViewModelProviders.of(this).get(RandomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)
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

    @Subscribe
    fun onAddThingEvent(event: AddThingEvent) {
        viewModel.addNewThing(event.thing)
    }

    override fun onDestroy() {
        KeyboardHelper.unRegisterKeyBoardListener(window)
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}
