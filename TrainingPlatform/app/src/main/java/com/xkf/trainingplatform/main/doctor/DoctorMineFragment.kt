package com.xkf.trainingplatform.main.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.FragmentDoctorMineBinding


class DoctorMineFragment : Fragment() {
    private lateinit var viewBinding: FragmentDoctorMineBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDoctorMineBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.exitButton.setOnClickListener {
            Global.doctor.apply {
                userName = ""
                password = ""
                id = ""
                isValidated = false
            }
            requireActivity().finish()
        }
    }
}