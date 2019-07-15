package com.example.workmanagertest

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {

        val outData = Data.Builder().putString("xkf","爱爽爽").build()

        return Result.success(outData)
    }
}