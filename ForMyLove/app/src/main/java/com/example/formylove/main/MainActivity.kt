package com.example.formylove.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import cn.bmob.v3.Bmob
import com.example.formylove.R
import com.example.formylove.kiss.KissFragment
import com.example.formylove.picturewall.PictureWallFragment
import com.example.formylove.things.ThingsFragment
import com.example.formylove.timeline.TimeLineFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun handleScroll(layoutManager: LinearLayoutManager, dx: Int) {
		val first = layoutManager.findFirstVisibleItemPosition()
		val firstView = layoutManager.findViewByPosition(first)!!
		val last = layoutManager.findLastVisibleItemPosition()
		val lastView = layoutManager.findViewByPosition(last)!!
		val contentView =
			layoutManager.findViewByPosition(layoutManager.findFirstCompletelyVisibleItemPosition())
		val width = firstView.width

		if (dx > 0) {
			//从右往左滑动<-------
			val fraction = (Math.abs(firstView.left).toFloat() / width.toFloat())
			val value = fraction * 0.3f
			firstView.scaleY = 1f - value
			firstView.scaleX = 1f - value
			lastView.scaleX = 0.7f + value
			lastView.scaleY = 0.7f + value

//			val startColor = Color.parseColor(model?.dataList?.get(first)?.color)
//			val endColor = Color.parseColor(model?.dataList?.get(last)?.color)
//			val currentColor = getCurrentColor(fraction, startColor, endColor)
//			view?.updateColor(currentColor)
		} else {
			//从左往右滑动------->
			val fraction = (Math.abs(firstView.right).toFloat() / width.toFloat())
			val value = fraction * 0.3f
			lastView.scaleY = 1f - value
			lastView.scaleX = 1f - value
			firstView.scaleX = 0.7f + value
			firstView.scaleY = 0.7f + value

//			val startColor = Color.parseColor(model?.dataList?.get(last)?.color)
//			val endColor = Color.parseColor(model?.dataList?.get(first)?.color)
//			val currentColor = getCurrentColor(fraction, startColor, endColor)
//			view?.updateColor(currentColor)
		}
		if (contentView != null) {
			contentView.scaleX = 1f
			contentView.scaleY = 1f
		}
	}
}
