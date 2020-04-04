package com.we.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.we.common.component.CommonHandler
import com.we.common.view.BaseFragment
import com.we.splash.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {
    private lateinit var viewBinding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun createView(): View {
        viewBinding = FragmentSplashBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun setupViews() {
        (activity as? CommonHandler)?.fullScreen()
        viewBinding.sbvBackgroundView.startAnimation()
        viewBinding.root.setOnClickListener {
            viewBinding.sbvBackgroundView.cancelAnimation()
            (activity as? SplashHandler)?.openMain()
        }
    }

    override fun onDestroyView() {
        viewBinding.sbvBackgroundView.cancelAnimation()
        super.onDestroyView()
    }
}
