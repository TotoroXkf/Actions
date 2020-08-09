package com.xkf.trainingplatform.authenticate

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.base.Global
import com.xkf.trainingplatform.databinding.ActivityAuthenticateBinding


class AuthenticateActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAuthenticateBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(AuthenticateViewModel::class.java)
    }

    private lateinit var inputInfoFragment: InputInfoFragment
    private lateinit var successFragment: SuccessFragment
    private lateinit var certificateFragment: CertificateFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_authenticate
        )
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this

        viewModel.step1Selected.observe(this, Observer { isSelected ->
            if (isSelected) {
                inputInfoFragment = InputInfoFragment()
                replaceFragment(inputInfoFragment)
            }
        })

        viewModel.step2Selected.observe(this, Observer { isSelected ->
            if (isSelected) {
                certificateFragment = CertificateFragment()
                replaceFragment(certificateFragment)
            }
        })

        viewModel.step3Selected.observe(this, Observer { isSelected ->
            if (isSelected) {
                successFragment = SuccessFragment()
                replaceFragment(successFragment)
            }
        })

        viewModel.step1Selected.value = true

        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        requestPermissions(permissions, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10 || requestCode == 20) {
            val uri = data?.data
            uri?.let { letUri ->
                val imagePath = getRealPathFromURI(letUri)

                if (requestCode == 10) {
                    Global.doctor.certificatePath = imagePath
                } else if (requestCode == 20) {
                    Global.doctor.avatarPath = imagePath
                }

                val bitmap = BitmapFactory.decodeFile(imagePath)
                if (requestCode == 10) {
                    certificateFragment.showImage(bitmap)
                } else if (requestCode == 20) {
                    inputInfoFragment.showAvatarImage(bitmap)
                }
            }
        }
    }

    private fun getRealPathFromURI(contentURI: Uri): String {
        val result: String
        val cursor = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path.toString()
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(viewBinding.containerLayout.id, fragment)
        transaction.commitAllowingStateLoss()
    }
}