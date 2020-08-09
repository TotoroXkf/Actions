package com.xkf.trainingplatform.authenticate

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.FragmentInputInfoBinding


class InputInfoFragment : Fragment() {
    private lateinit var viewBinding: FragmentInputInfoBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthenticateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentInputInfoBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.avatarImageView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            requireActivity().startActivityForResult(intent, 20)
        }

        viewBinding.nextButton.setOnClickListener {
            val sex = viewBinding.sexInputLayout.editText?.text?.toString() ?: ""
            if (sex.isNotEmpty()) {
                Global.doctor.sex = sex
            }

            val age = viewBinding.ageInputLayout.editText?.text?.toString() ?: ""
            if (age.isNotEmpty()) {
                Global.doctor.age = age
            }

            val hospital = viewBinding.hospitalInputLayout.editText?.text?.toString() ?: ""
            if (hospital.isNotEmpty()) {
                Global.doctor.hospital = hospital
            }

            val department = viewBinding.departmentInputLayout.editText?.text?.toString() ?: ""
            if (department.isNotEmpty()) {
                Global.doctor.department = department
            }

            val jobTitle = viewBinding.jobTitleInputLayout.editText?.text?.toString() ?: ""
            if (jobTitle.isNotEmpty()) {
                Global.doctor.jobTitle = jobTitle
            }

            viewModel.step1Selected.value = false
            viewModel.step2Selected.value = true
        }
    }

    fun showAvatarImage(bitmap: Bitmap) {
        viewBinding.avatarImageView.setImageBitmap(bitmap)
    }
}