package com.xkf.trainingplatform.main.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkf.trainingplatform.databinding.FragmentDoctorKnowledgeBinding
import com.xkf.trainingplatform.feature.TextDetailActivity


class DoctorKnowledgeFragment : Fragment() {
    private lateinit var viewBinding: FragmentDoctorKnowledgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDoctorKnowledgeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.cardView1.setOnClickListener {
            TextDetailActivity.startActivity(requireActivity(), 0)
        }

        viewBinding.cardView2.setOnClickListener {
            TextDetailActivity.startActivity(requireActivity(), 1)
        }

        viewBinding.cardView3.setOnClickListener {
            TextDetailActivity.startActivity(requireActivity(), 2)
        }
    }
}