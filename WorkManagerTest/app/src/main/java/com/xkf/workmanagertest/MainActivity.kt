package com.xkf.workmanagertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = workDataOf("input" to "input")
        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
            .setInputData(data)
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this, Observer {

            })
    }
}
