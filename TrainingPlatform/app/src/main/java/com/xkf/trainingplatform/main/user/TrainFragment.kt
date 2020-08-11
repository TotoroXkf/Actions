package com.xkf.trainingplatform.main.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkf.trainingplatform.databinding.FragmentTrainBinding
import com.xkf.trainingplatform.feature.Video1Activity


class TrainFragment : Fragment() {
    private lateinit var viewBinding: FragmentTrainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTrainBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.materialButton1.setOnClickListener {
            startActivity(Intent(requireActivity(), Video1Activity::class.java))
        }
    }
}