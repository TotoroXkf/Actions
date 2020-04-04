package com.we.formylove.fragment

import android.view.View
import com.bumptech.glide.Glide
import com.we.common.component.CommonHandler
import com.we.common.view.BaseFragment
import com.we.formylove.databinding.FragmentMainPageBinding
import com.we.formylove.viewmodel.MainViewModel


class MainPageFragment(val viewModel: MainViewModel, private val index: Int) : BaseFragment() {
    private lateinit var viewBinding: FragmentMainPageBinding

    override fun initViewModel() {

    }

    override fun createView(): View {
        viewBinding = FragmentMainPageBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        viewBinding.index = index
        return viewBinding.root
    }

    override fun setupViews() {
        Glide.with(viewBinding.imageView)
            .load(viewModel.mainPageList.value!![index].imageUrl)
            .into(viewBinding.imageView)

        viewBinding.buttonView.setOnClickListener {
            (activity as? CommonHandler)?.open(viewModel.mainPageList.value!!.get(index).jumpUrl)
        }
    }
}
