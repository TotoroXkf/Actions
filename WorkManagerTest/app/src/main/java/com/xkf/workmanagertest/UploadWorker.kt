package com.xkf.workmanagertest

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

/**
 * author : xiakaifa
 * 2020/5/9
 */

const val Progress = "Progress"

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val firstUpdate = workDataOf(Progress to 0)
        val lastUpdate = workDataOf(Progress to 100)
        setProgress(firstUpdate)
        upload()
        setProgress(lastUpdate)
        return Result.success()
    }

    fun upload() {
        Thread.sleep(3000)
    }
}