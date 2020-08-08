package com.xkf.trainingplatform.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.base.UserManager
import com.xkf.trainingplatform.databinding.ActivityRegisterDetailBinding
import com.xkf.trainingplatform.main.MainActivity

class RegisterDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var viewBinding: ActivityRegisterDetailBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(RegisterDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_detail)
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this

        viewBinding.userNameInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                checkButtonState()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })

        viewBinding.IdInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                checkButtonState()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })

        viewBinding.passwordInputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        viewBinding.confirmPasswordInputLayout.editText?.addTextChangedListener(object :
            TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        viewBinding.postButton.setOnClickListener(this)
    }

    fun checkButtonState() {
        val userName = viewBinding.userNameInputLayout.editText?.text?.toString() ?: ""
        val id = viewBinding.IdInputLayout.editText?.text?.toString() ?: ""
        val password = viewBinding.passwordInputLayout.editText?.text?.toString() ?: ""
        val confirmPassword =
            viewBinding.confirmPasswordInputLayout.editText?.text?.toString() ?: ""

        viewModel.postButtonEnable.value =
            userName.isNotEmpty() && id.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }

    override fun onClick(v: View) {
        val userName = viewBinding.userNameInputLayout.editText?.text?.toString() ?: ""
        if (userName.isEmpty()) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        val id = viewBinding.IdInputLayout.editText?.text?.toString() ?: ""
        if (id.isEmpty()) {
            Toast.makeText(this, "身份证号不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        val password = viewBinding.passwordInputLayout.editText?.text?.toString() ?: ""
        if (password.isEmpty()) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }

        val confirmPassword =
            viewBinding.confirmPasswordInputLayout.editText?.text?.toString() ?: ""
        if (confirmPassword.isEmpty() || confirmPassword != password) {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show()
            return
        }

        UserManager.register(userName, password)
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
    }
}