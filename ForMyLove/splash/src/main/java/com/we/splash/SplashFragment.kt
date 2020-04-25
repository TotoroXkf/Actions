package com.we.splash

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.we.common.component.CommonHandler
import com.we.common.view.BaseFragment
import com.we.splash.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {
    companion object {
        const val NAME = "splashFragment"
    }

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
        (requireActivity() as? CommonHandler)?.fullScreen()
        viewBinding.sbvBackgroundView.startAnimation()
        viewBinding.root.setOnClickListener {
            viewBinding.sbvBackgroundView.cancelAnimation()
            findNavController().popBackStack()
        }
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewBinding.sbvBackgroundView.cancelAnimation()
            }
        })
    }

    override fun onDestroyView() {
        viewBinding.sbvBackgroundView.cancelAnimation()
        super.onDestroyView()
    }
}
