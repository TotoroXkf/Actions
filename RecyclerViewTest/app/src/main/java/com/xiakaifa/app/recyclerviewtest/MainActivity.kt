package com.xiakaifa.app.recyclerviewtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    handleScroll(layoutManager, dx)
                }
            }
        })
    }

    fun handleScroll(layoutManager: LinearLayoutManager, dx: Int) {
        val first = layoutManager.findFirstVisibleItemPosition()
        val firstView = layoutManager.findViewByPosition(first)!!
        val last = layoutManager.findLastVisibleItemPosition()
        val lastView = layoutManager.findViewByPosition(last)!!
        val contentView =
            layoutManager.findViewByPosition(layoutManager.findFirstCompletelyVisibleItemPosition())
        val width = firstView.width
        Log.e("xkf",""+firstView.right)
        if (dx > 0) {
            //从右往左滑动
            val value = (Math.abs(firstView.left).toFloat() / width.toFloat()) * 0.3f
            firstView.scaleY = 1f - value
            firstView.scaleX = 1f - value
            lastView.scaleX = 0.7f + value
            lastView.scaleY = 0.7f + value
        } else {
            //从左往右滑动
            val value = (Math.abs(firstView.right).toFloat() / width.toFloat()) * 0.3f
            lastView.scaleY = 1f - value
            lastView.scaleX = 1f - value
            firstView.scaleX = 0.7f + value
            firstView.scaleY = 0.7f + value
        }
        if (contentView != null) {
            contentView.scaleX = 1f
            contentView.scaleY = 1f
        }
    }
}
