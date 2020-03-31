package formylove.base

import android.app.Application
import formylove.utils.ImageLoader
import formylove.utils.ToastHelper

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ImageLoader.init(this)
        ToastHelper.init(this)
    }
}