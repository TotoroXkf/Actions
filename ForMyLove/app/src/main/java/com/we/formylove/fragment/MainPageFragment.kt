package com.we.formylove.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.we.common.component.CommonHandler
import com.we.formylove.databinding.FragmentMainPageBinding
import com.we.formylove.viewmodel.MainViewModel


class MainPageFragment(val viewModel: MainViewModel, private val index: Int) : Fragment() {
    private lateinit var viewBinding: FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainPageBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        viewBinding.index = index
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(viewBinding.imageView)
            .load(viewModel.mainPageList.value!![index].imageUrl)
            .into(viewBinding.imageView)

        viewBinding.buttonView.setOnClickListener {
            (activity as? CommonHandler)?.open(viewModel.mainPageList.value!!.get(index).jumpUrl)
        }
    }
}
