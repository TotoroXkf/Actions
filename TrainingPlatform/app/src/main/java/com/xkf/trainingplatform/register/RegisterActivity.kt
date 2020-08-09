package com.xkf.trainingplatform.register

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.base.TYPE_DOCTOR
import com.xkf.trainingplatform.base.TYPE_USER
import com.xkf.trainingplatform.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewBinding.btnUser.setOnClickListener(this)
        viewBinding.buttonDoctor.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            viewBinding.buttonDoctor.id -> {
                RegisterDetailActivity.startActivity(this, TYPE_DOCTOR)
            }
            viewBinding.btnUser.id -> {
                RegisterDetailActivity.startActivity(this, TYPE_USER)
            }
        }
    }
}