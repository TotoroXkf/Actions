package com.we.formylove.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.we.common.component.CommonHandler
import com.we.formylove.databinding.FragmentMainBinding
import com.we.formylove.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? CommonHandler)?.cancelFullScreen()

        viewBinding = FragmentMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }


}
