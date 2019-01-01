package com.example.todotest.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.todotest.R

abstract class BaseActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(getLayoutId())
		setUpFragment(savedInstanceState)
	}
	
	private fun setUpFragment(savedInstanceState: Bundle?) {
		if (savedInstanceState == null) {
			supportFragmentManager
				.beginTransaction()
				.replace(R.id.fragment_container, getFragment())
				.commit()
		}
	}
	
	protected abstract fun getFragment(): Fragment
	
	protected abstract fun getLayoutId(): Int
}