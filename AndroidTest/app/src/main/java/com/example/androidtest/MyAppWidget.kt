package com.example.androidtest

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.SystemClock
import android.widget.RemoteViews

const val TAG = "xiakaifa"
const val CLICK_ACTION = "clickAction"

class MyAppWidget : AppWidgetProvider() {
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        context ?: return
        if (intent?.action == CLICK_ACTION) {
            Thread {
                val appWidgetManager = AppWidgetManager.getInstance(context)
                val bitmap =
                    BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher_round)
                for (i in 0 until 37) {
                    // 设置View
                    val degree = (i * 10) % 360
                    val newBitmap = rotateBitmap(bitmap, degree)
                    val remoteViews = RemoteViews(context.packageName, R.layout.widget)
                    remoteViews.setImageViewBitmap(R.id.imageView, newBitmap)

                    // 设置intent
                    val clickIntent = Intent()
                    clickIntent.action = CLICK_ACTION
                    val pendingIntent = PendingIntent.getBroadcast(
                        context,
                        0,
                        clickIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    remoteViews.setOnClickPendingIntent(R.id.imageView, pendingIntent)

                    // 更新部件
                    appWidgetManager.updateAppWidget(
                        ComponentName(context, MyAppWidget::class.java),
                        remoteViews
                    )
                    SystemClock.sleep(30)
                }
            }.start()
        }
    }


    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.reset()
        matrix.setRotate(degree.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}