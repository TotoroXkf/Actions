package formylove.statement

import android.animation.ObjectAnimator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.formylove.R
import formylove.base.BaseActivity
import com.xiasuhuei321.loadingdialog.view.LoadingDialog
import kotlinx.android.synthetic.main.activity_statement.*
import kotlinx.coroutines.*


class StatementActivity : BaseActivity(), CoroutineScope by MainScope() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(StatementViewModel::class.java)
    }
    
    private val adapter by lazy {
        StatementAdapter(viewModel)
    }
    
    private lateinit var loadingDialog: LoadingDialog
    
    private var fabIsHide = false
    private val fabAnimation = ObjectAnimator()
    
    override fun getLayoutId(): Int = R.layout.activity_statement
    
    override fun initViewModel() {
        viewModel.deleteLiveData.observe(this, Observer {
            adapter.notifyItemRemoved(it)
        })
        
        viewModel.dialogLiveData.observe(this, Observer { show: Boolean ->
            if (show) {
                loadingDialog.show()
            } else {
                loadingDialog.close()
            }
        })
    }
    
    override fun initViews() {
        setStatusBarWhite()
        
        loadingDialog = LoadingDialog(this).setLoadingText("喵喵喵~~")
        
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
        
        refreshLayout.setOnRefreshListener {
            refresh()
        }
        
        refresh()
    }
    
    private fun handleInputDialog() {
        MaterialDialog(this).show {
            input { _, text ->
                GlobalScope.launch(Dispatchers.Main) {
                    viewModel.uploadNewStatement(text.toString())
                    refresh()
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
        if (fabIsHide && dy <= 0) {
            fabIsHide = false
            fabAnimation.reverse()
        } else if (!fabIsHide && dy > 0) {
            fabIsHide = true
            fabAnimation.setFloatValues(
                floatingActionButton.y,
                floatingActionButton.y + 500f
            )
            fabAnimation.start()
        }
    }
}
