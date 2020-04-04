package com.we.lovestatement.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.we.common.view.BaseFragment
import com.we.lovestatement.R
import com.we.lovestatement.databinding.FragmentLoveStatementBinding
import com.we.lovestatement.databinding.ItemStatementBinding
import com.we.lovestatement.fragment.LoveStatementViewModel
import kotlinx.android.synthetic.main.fragment_love_statement.*

class LoveStatementFragment : BaseFragment() {
    companion object {
        const val NAME = "loveStatementFragment"
    }

    private lateinit var viewBinding: FragmentLoveStatementBinding
    private lateinit var viewModel: LoveStatementViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoveStatementViewModel::class.java)
    }

    override fun createView(): View {
        viewBinding = FragmentLoveStatementBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun setupViews() {
        recyclerView.adapter = Adapter()
    }

    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemViewBinding: ItemStatementBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_statement,
                parent,
                false
            )
            itemViewBinding.viewModel = viewModel
            return StatementViewHolder(itemViewBinding.root)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as StatementViewHolder).bind()
        }
    }

    inner class SpaceDecoration : RecyclerView.ItemDecoration() {

    }

    inner class StatementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemViewBinding: ItemStatementBinding = DataBindingUtil.bind(itemView)!!

        fun bind() {
            itemViewBinding.textView.text = "爱大喵"
        }
    }
}
