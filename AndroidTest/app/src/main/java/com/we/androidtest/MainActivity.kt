package com.we.androidtest

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_PICK = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCall(view: View) {
        val hasCallPhonePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED
        if (hasCallPhonePermission)
            startActivity(createCallIntentFromNumber())
        else
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show()
    }

    fun onPickContact(view: View) {
        val pickContactIntent = Intent(this, Main2Activity::class.java)
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK)
    }

    private fun createCallIntentFromNumber(): Intent? {
        val intentToCall = Intent(Intent.ACTION_CALL)
        val number: String = edit_text_caller_number.getText().toString()
        intentToCall.data = Uri.parse("tel:$number")
        return intentToCall
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                edit_text_caller_number.setText(data?.extras?.getString(Main2Activity.KEY_PHONE_NUMBER))
            }
        }
    }
}
