package com.xkf.trainingplatform.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xkf.trainingplatform.databinding.FragmentKnowledgeBinding


class KnowledgeFragment : Fragment() {
    private lateinit var viewBinding: FragmentKnowledgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentKnowledgeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.cardView1.setOnClickListener {

        }

        viewBinding.cardView2.setOnClickListener {

        }

        viewBinding.cardView3.setOnClickListener {

        }
    }
}