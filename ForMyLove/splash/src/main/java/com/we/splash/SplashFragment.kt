package com.we.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.we.common.component.CommonHandler
import com.we.splash.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var viewBinding: FragmentSplashBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? CommonHandler)?.fullScreen()

        viewBinding = FragmentSplashBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.sbvBackgroundView.startAnimation()

        viewBinding.root.setOnClickListener {
            (activity as? SplashHandler)?.startMain()
        }
    }
}
