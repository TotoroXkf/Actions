package com.we.androidtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

val KEY_URL_TO_LOAD = "KEY_URL_TO_LOAD"

@VisibleForTesting
val WEB_FORM_URL = "file:///android_asset/web_form.html"

open class MainActivity : AppCompatActivity() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(urlFromIntent(intent))
        web_view.requestFocus()
        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
    }

    private fun urlFromIntent(intent: Intent): String? {
        val url = intent.getStringExtra(KEY_URL_TO_LOAD)
        return if (!TextUtils.isEmpty(url)) url else WEB_FORM_URL
    }
}
