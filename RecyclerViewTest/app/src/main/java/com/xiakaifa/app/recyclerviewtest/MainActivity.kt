package com.xiakaifa.app.recyclerviewtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.PagerSnapHelper


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

    }
}
