package com.xkf.databingdingtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xkf.databingdingtest.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityThirdBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_third)
        val liveData = MutableLiveData<String>("button")
        binding.text = liveData.value
    }
}