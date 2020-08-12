package com.xkf.filetest

import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

const val TAG = "dalongmao"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "getExternalStoragePublicDirectory: " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).path)
        Log.e(TAG, "getExternalFilesDir: " + getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.path)


    }
}