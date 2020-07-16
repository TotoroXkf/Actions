package com.xkf.databingdingtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xkf.databingdingtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.isChecked.observe(this, Observer { isChecked: Boolean ->
            viewModel.onCheckedChange(isChecked)
        })

//        startActivity(Intent(this, SecondActivity::class.java))

        startActivity(Intent(this, ThirdActivity::class.java))
    }
}