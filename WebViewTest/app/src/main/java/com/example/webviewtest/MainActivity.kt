package com.example.webviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebViewClient
import android.widget.Toast
import com.github.lzyzsd.jsbridge.BridgeHandler
import com.github.lzyzsd.jsbridge.CallBackFunction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.loadUrl("file:///android_asset/web/test.html")
        webView.registerHandler("submitFromWeb") { data, function ->
            Toast.makeText(this@MainActivity, data, Toast.LENGTH_SHORT).show()
            function.onCallBack("xkf")
        }
    }
}
