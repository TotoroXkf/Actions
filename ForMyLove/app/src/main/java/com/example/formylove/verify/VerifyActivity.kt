package com.example.formylove.verify

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.formylove.R
import com.example.formylove.MainActivity
import kotlinx.android.synthetic.main.activity_verify.*

class VerifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        btn.setOnClickListener {
            val text = til_password.editText?.text?.toString()
            if (text != null) {
                if (text == "1120" || text == "0627") {
                    startActivity(Intent(this@VerifyActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@VerifyActivity, "你好像走错地方了！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
