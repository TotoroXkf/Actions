package com.example.myapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import android.view.MenuItem
import com.example.myapp.datecompute.DateComputeFragment
import com.example.myapp.randomthings.RandomThingsFragment
import com.example.myapp.weather.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger


class MainActivity : AppCompatActivity(), AnkoLogger {
    val mTabNames = arrayListOf("随机事件", "天气", "日期计算")
    val mFragments = arrayListOf(RandomThingsFragment(), WeatherFragment(), DateComputeFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        //todo 加上右上角的三点菜单
    }

    private fun initViews() {
        setSupportActionBar(toolbar)

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }

            override fun getCount(): Int {
                return mTabNames.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mTabNames[position]
            }
        }

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /*
     * 菜单的点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //todo 写todo事件
        }
        return true
    }


}
