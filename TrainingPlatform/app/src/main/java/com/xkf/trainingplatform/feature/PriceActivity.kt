package com.xkf.trainingplatform.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.databinding.ActivityPriceBinding


class PriceActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPriceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_price)
    }
}