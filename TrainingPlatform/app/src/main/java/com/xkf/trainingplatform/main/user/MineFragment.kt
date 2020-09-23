package com.xkf.trainingplatform.main.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.FragmentMineBinding
import com.xkf.trainingplatform.feature.CourseRecordActivity
import com.xkf.trainingplatform.feature.PriceActivity
import com.xkf.trainingplatform.feature.TrainRecordActivity


class MineFragment : Fragment() {
    private lateinit var viewBinding: FragmentMineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMineBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.priceBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), PriceActivity::class.java))
        }

        viewBinding.btnCourseRecord.setOnClickListener {
            startActivity(Intent(requireActivity(), CourseRecordActivity::class.java))
        }

        viewBinding.trainRecordBtn.setOnClickListener {
            startActivity(Intent(requireActivity(), TrainRecordActivity::class.java))
        }

        viewBinding.exitButton.setOnClickListener {
            Global.user.apply {
                userName = ""
                password = ""
                id = ""
                isValidated = false
            }
            requireActivity().finish()
        }
    }
}