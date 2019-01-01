package com.example.todotest.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.todotest.R
import com.example.todotest.base.BaseActivity
import kotlinx.android.synthetic.main.base_toolbar.*

class MainActivity : BaseActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(false)
	}
	
	override fun getFragment(): Fragment {
	
	}
	
	override fun getLayoutId(): Int = R.layout.activity_main
	
}
