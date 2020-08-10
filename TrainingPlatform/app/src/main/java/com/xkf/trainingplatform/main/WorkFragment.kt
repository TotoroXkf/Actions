package com.xkf.trainingplatform.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xkf.trainingplatform.databinding.FragmentWorkBinding


class WorkFragment : Fragment() {
    private lateinit var viewBinding: FragmentWorkBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(WorkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentWorkBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.viewModel = viewModel
    }
}