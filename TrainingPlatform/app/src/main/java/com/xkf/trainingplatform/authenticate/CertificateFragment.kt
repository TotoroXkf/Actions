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
import com.xkf.trainingplatform.databinding.FragmentCertificateBinding


class CertificateFragment : Fragment() {
    private lateinit var viewBinding: FragmentCertificateBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(AuthenticateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCertificateBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.selectButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            requireActivity().startActivityForResult(intent, 10)
        }

        viewBinding.nextStepButton.setOnClickListener {
            viewModel.step2Selected.value = false
            viewModel.step3Selected.value = true
        }
    }

    fun showImage(bitmap: Bitmap) {
        viewBinding.imageView.setImageBitmap(bitmap)
    }
}