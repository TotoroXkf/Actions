package com.we.lovestatement

import android.view.View
import com.we.common.view.BaseFragment
import com.we.lovestatement.databinding.FragmentLoveStatementBinding

class LoveStatementFragment : BaseFragment() {
    companion object {
        const val NAME = "loveStatementFragment"
    }

    private lateinit var viewBinding: FragmentLoveStatementBinding

    override fun initViewModel() {

    }

    override fun createView(): View {
        val viewBinding = FragmentLoveStatementBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun setupViews() {

    }
}
