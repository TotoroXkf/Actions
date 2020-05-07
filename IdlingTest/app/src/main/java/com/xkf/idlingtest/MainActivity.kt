package com.xkf.idlingtest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingResource


class MainActivity : AppCompatActivity(), View.OnClickListener, MessageDelayer.DelayerCallback {
    private lateinit var mTextView: TextView
    private lateinit var mEditText: EditText
    private var mIdlingResource: SimpleIdlingResource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.changeTextBt).setOnClickListener(this);

        mTextView = findViewById(R.id.textToBeChanged);
        mEditText = findViewById(R.id.editTextUserInput);
    }

    override fun onClick(v: View) {
        val text = mEditText.text.toString()

        if (v.id == R.id.changeTextBt) {
            mTextView.text = "等待"
            MessageDelayer.processMessage(text, this, mIdlingResource)
        }
    }

    override fun onDone(text: String) {
        mTextView.text = text;
    }

    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        if (mIdlingResource == null) {
            mIdlingResource = SimpleIdlingResource()
        }
        return mIdlingResource!!
    }
}
