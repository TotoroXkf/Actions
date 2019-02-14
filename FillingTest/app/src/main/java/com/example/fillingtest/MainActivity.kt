package com.example.fillingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
		recycleView.adapter = MyAdapter()
		recycleView.layoutManager = layoutManager
		
	}
	
	class MyAdapter : RecyclerView.Adapter<ViewHolder>() {
		override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
			return ViewHolder(
				LayoutInflater.from(viewGroup.context).inflate(
					R.layout.item_layout,
					viewGroup,
					false
				)
			)
		}
		
		override fun getItemCount(): Int = 1000
		
		override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
		}
		
	}
	
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	
	}
}
