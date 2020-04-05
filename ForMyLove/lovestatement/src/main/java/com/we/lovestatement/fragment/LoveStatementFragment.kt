package com.we.lovestatement.fragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.we.common.component.CommonUtils
import com.we.common.view.BaseFragment
import com.we.lovestatement.databinding.FragmentLoveStatementBinding
import com.we.lovestatement.databinding.ItemStatementBinding
import com.we.lovestatement.viewmodel.LoveStatementViewModel

class LoveStatementFragment : BaseFragment() {
    companion object {
        const val NAME = "loveStatementFragment"
    }

    private lateinit var viewBinding: FragmentLoveStatementBinding
    private lateinit var viewModel: LoveStatementViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoveStatementViewModel::class.java)
        viewModel.loveStatementList.observe(this, Observer {
            viewBinding.refreshLayoutView.isRefreshing = false
            refreshView()
        })
        viewModel.loadStatementList()
    }

    override fun createView(): View {
        viewBinding = FragmentLoveStatementBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun refreshView() {
        setupViews()
    }

    override fun setupViews() {
        viewBinding.recyclerView.adapter = Adapter()
        val size: Int = viewBinding.recyclerView.itemDecorationCount
        if (size > 0) {
            viewBinding.recyclerView.removeItemDecorationAt(0);
        }
        viewBinding.recyclerView.addItemDecoration(SpaceDecoration())

        viewBinding.refreshLayoutView.setOnRefreshListener {

            viewModel.loadStatementList()
        }
    }

    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemViewBinding: ItemStatementBinding =
                ItemStatementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return StatementViewHolder(itemViewBinding.root)
        }

        override fun getItemCount(): Int {
            return viewModel.getLoveStatementList().size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as? StatementViewHolder)?.bind()
        }
    }

    inner class SpaceDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(
                outRect,
                view,
                parent,
                state
            )
            when (parent.getChildAdapterPosition(view)) {
                0 -> {
                    outRect.top = CommonUtils.dpToPx(10f).toInt()
                    outRect.bottom = CommonUtils.dpToPx(20f).toInt()
                }
                ((parent.adapter?.itemCount ?: 0) - 1) -> {
                    outRect.bottom = CommonUtils.dpToPx(86f).toInt()
                }
                else -> {
                    outRect.bottom = CommonUtils.dpToPx(20f).toInt()
                }
            }

        }
    }

    inner class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemViewBinding: ItemStatementBinding = DataBindingUtil.getBinding(itemView)!!

        fun bind() {
            val text = viewModel.getLoveStatementList()[bindingAdapterPosition].statement
            itemViewBinding.textView.text = text
        }
    }
}
