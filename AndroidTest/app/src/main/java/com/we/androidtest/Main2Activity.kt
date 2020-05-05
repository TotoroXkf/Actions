package com.we.androidtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity


class Main2Activity : AppCompatActivity() {
    companion object {
        const val KEY_PHONE_NUMBER = "key_phone_number"

        @VisibleForTesting
        fun createResultData(phoneNumber: String?): Intent? {
            val resultData = Intent()
            resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber)
            return resultData
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setResult(Activity.RESULT_OK, createResultData("896-745-231"))
        finish()
    }
}
