package com.xkf.trainingplatform.authenticate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.main.MainActivity

class SuccessFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.finishButton).setOnClickListener {
            requireActivity().finish()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
    }
}