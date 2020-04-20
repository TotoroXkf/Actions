package com.we.answerbook

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.we.answerbook.databinding.AnswerBookFragmentBinding
import com.we.common.view.BaseFragment


class AnswerBookFragment : BaseFragment() {
    companion object {
        const val NAME = "answerBookFragment"
    }

    private lateinit var viewModel: AnswerBookViewModel
    private lateinit var viewBinding: AnswerBookFragmentBinding

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AnswerBookViewModel::class.java)
    }

    override fun createView(): View {
        viewBinding = AnswerBookFragmentBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun setupViews() {

    }
}
