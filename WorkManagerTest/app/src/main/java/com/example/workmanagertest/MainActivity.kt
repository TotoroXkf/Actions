package com.example.workmanagertest

import android.app.DownloadManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(this).enqueue(request)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this, Observer { info ->
            if (info != null && info.state == WorkInfo.State.SUCCEEDED) {
                val message = info.outputData.getString("xkf")
            }
        })
        WorkManager.getInstance(this).cancelWorkById(request.id)
    }
}
