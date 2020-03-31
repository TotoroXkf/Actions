package formylove.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

@SuppressLint("StaticFieldLeak")
object ToastHelper {
    private var context: Context? = null
    private var toast: Toast? = null
    
    @SuppressLint("ShowToast")
    fun init(context: Context) {
        this.context = context
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
    }
    
    fun toast(message: String) {
        if (context == null || toast == null) {
            return
        }
        toast?.setText(message)
        toast?.show()
    }
}

fun toast(message: String) {
    ToastHelper.toast(message)
}