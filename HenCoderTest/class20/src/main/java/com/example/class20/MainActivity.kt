package com.example.class20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import test

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        test()
    }
}
