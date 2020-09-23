package com.xkf.trainingplatform.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xkf.trainingplatform.RandomAskActivity
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.FragmentWorkBinding
import com.xkf.trainingplatform.feature.AskActivity
import com.xkf.trainingplatform.feature.InputUserActivity
import com.xkf.trainingplatform.feature.TreatmentCountActivity
import com.xkf.trainingplatform.feature.select.SelectDoctorActivity


class WorkFragment : Fragment(), View.OnClickListener {
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

        viewBinding.boardLayout1.setOnClickListener(this)
        viewBinding.boardLayout2.setOnClickListener(this)
        viewBinding.boardLayout3.setOnClickListener(this)
        viewBinding.boardLayout4.setOnClickListener(this)
        viewBinding.boardLayout5.setOnClickListener(this)
        viewBinding.boardLayout6.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            viewBinding.boardLayout1.id -> {
                if (Global.isDoctor()) {
                    startActivity(Intent(requireActivity(), InputUserActivity::class.java))
                } else if (Global.isUser()) {
                    startActivity(Intent(requireActivity(), AskActivity::class.java))
                }
            }
            viewBinding.boardLayout3.id -> {
                if (Global.isDoctor()) {
                    startActivity(Intent(requireActivity(), TreatmentCountActivity::class.java))
                }
            }
            viewBinding.boardLayout4.id -> {
                if (Global.isDoctor()) {
                    startActivity(Intent(requireActivity(), RandomAskActivity::class.java))
                } else if (Global.isUser()) {
                    startActivity(Intent(requireActivity(), TreatmentCountActivity::class.java))
                }
            }
            viewBinding.boardLayout5.id -> {
                if (Global.isUser()) {
                    startActivity(Intent(requireActivity(), SelectDoctorActivity::class.java))
                }
            }
        }
    }
}