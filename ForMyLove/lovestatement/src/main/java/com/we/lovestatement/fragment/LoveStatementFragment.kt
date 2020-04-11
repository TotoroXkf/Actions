package com.we.lovestatement.fragment

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.getInputField
import com.afollestad.materialdialogs.input.input
import com.we.common.component.CommonUtils
import com.we.common.view.BaseFragment
import com.we.lovestatement.R
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
        viewBinding.recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun setupViews() {
        viewBinding.recyclerView.adapter = Adapter()
        val size: Int = viewBinding.recyclerView.itemDecorationCount
        if (size > 0) {
            viewBinding.recyclerView.removeItemDecorationAt(0)
        }
        viewBinding.recyclerView.addItemDecoration(SpaceDecoration())

        viewBinding.refreshLayoutView.setOnRefreshListener {
            viewModel.loadStatementList()
        }

        viewBinding.floatingActionButton.setOnClickListener {
            MaterialDialog(requireActivity()).show {
                title(text = "喵喵喵")
                input(hint = "输入新的恋爱语句")
                positiveButton(text = "确定") {
                    viewBinding.refreshLayoutView.isRefreshing = true
                    viewModel.addStatement(it.getInputField().editableText.toString())
                }
            }
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
            itemViewBinding.root.setOnLongClickListener {
                showPopMenu()
                true
            }
        }

        private fun showPopMenu() {
            val popMenu = PopupMenu(activity, itemViewBinding.root)
            popMenu.menuInflater.inflate(R.menu.long_click_pop_menu, popMenu.menu)
            popMenu.show()
            popMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.update -> {
                        onClickUpdate()
                    }
                    R.id.delete -> {
                        onClickDelete()
                    }
                }
                true
            }
        }

        private fun onClickDelete() {
            MaterialDialog(activity!!).show {
                title(text = "删除这条语句？")
                message(text = "确认要删除这条语句吗？")
                positiveButton(text = "删掉吧，不要啦") {
                    viewBinding.refreshLayoutView.isRefreshing = true
                    viewModel.deleteStatement(bindingAdapterPosition)
                }
                negativeButton(text = "喵喵，在考虑一下") {

                }
            }
        }

        private fun onClickUpdate() {
            val text = viewModel.getLoveStatementList()[bindingAdapterPosition].statement
            MaterialDialog(activity!!).show {
                input(prefill = text)
                positiveButton(text = "确定") {
                    val newText = it.getInputField().editableText.toString()
                    viewModel.updateStatement(bindingAdapterPosition, newText)
                }
            }
        }
    }
}
