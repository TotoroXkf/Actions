package com.example.formylove

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import cn.bmob.v3.Bmob
import com.example.formylove.picturewall.PictureWallFragment
import com.example.formylove.things.ThingsFragment
import com.example.formylove.timeline.TimeLineFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val apiKey = "35a21ee65f184b928506cc68bd28cfeb"

    val tabNames = arrayOf("摇事件", "照片墙", "时间线")
    val fragments = arrayOf(ThingsFragment(), PictureWallFragment(), TimeLineFragment())

    @SuppressLint("UseValueOf")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Bmob.initialize(this, apiKey)

        setSupportActionBar(appbar)

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return tabNames.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return tabNames[position]
            }
        }

        tabs.setupWithViewPager(viewPager)
    }
}
