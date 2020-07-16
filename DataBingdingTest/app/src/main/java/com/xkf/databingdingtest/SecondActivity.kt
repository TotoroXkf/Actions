package com.xkf.databingdingtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xkf.databingdingtest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = "https://s1.ax1x.com/2020/03/31/GMgyaF.jpg"
        binding.url = imageUrl
    }
}