package formylove.turntable

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import formylove.base.AddThingEvent
import formylove.base.BaseActivity
import formylove.utils.KeyboardHelper
import kotlinx.android.synthetic.main.activity_random.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class TurnTableActivity : BaseActivity() {
    private val adapter = ListAdapter()

    private val viewModel: TurntableViewModel by lazy {
        ViewModelProviders.of(this).get(TurntableViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        EventBus.getDefault().register(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_random

    override fun initViewModel() {
        viewModel.addLiveData.observe(this, Observer {
            adapter.notifyItemInserted(adapter.itemCount - 1)
            turntableView.invalidate()
        })

        viewModel.deleteLiveData.observe(this, Observer {
            adapter.notifyItemRemoved(it)
            turntableView.invalidate()
        })
    }

    override fun initViews() {
        setStatusBarWhite()

        turntableView.setColorList(viewModel.colorList)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.setData(viewModel.colorList, viewModel.thingsList)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        val callback = DeleteItemTouchHelperCallback()
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

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

    inner class DeleteItemTouchHelperCallback :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.deleteThing(viewHolder.adapterPosition)
        }
    }
}
