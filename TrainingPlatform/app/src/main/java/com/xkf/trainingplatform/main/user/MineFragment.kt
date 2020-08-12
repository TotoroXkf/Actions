package com.xkf.trainingplatform.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.FragmentMineBinding
import kotlin.system.exitProcess


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

        viewBinding.exitButton.setOnClickListener {
            Global.user.apply {
                userName = ""
                password = ""
                id = ""
                isValidated = false
            }
            exitProcess(0)
        }
    }
}