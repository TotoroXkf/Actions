package com.example.camera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		Thread {
			while (true) {
				val socket = Socket("192.168.1.9", 10086)
				val input = socket.getInputStream()
				val reader = BufferedReader(InputStreamReader(input))
				val info = reader.readLine()
				
				reader.close()
				socket.close()
			}
		}.start()
	}
}
