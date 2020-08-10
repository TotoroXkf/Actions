package com.xkf.trainingplatform.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.xkf.trainingplatform.R
import com.xkf.trainingplatform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityMainBinding
    private val fragmentList = arrayListOf<Fragment>(
        WorkFragment(),
        TrainFragment(),
        KnowledgeFragment(),
        MineFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewBind.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewBind.bottomBar.selectedItemId = viewBind.bottomBar.menu.getItem(position).itemId
            }
        })
        viewBind.viewPager.adapter = ViewPageAdapter(this)

        viewBind.bottomBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_work -> {
                    viewBind.viewPager.setCurrentItem(0, true)
                }
                R.id.menu_knowledge -> {
                    viewBind.viewPager.setCurrentItem(1, true)
                }
                R.id.menu_train -> {
                    viewBind.viewPager.setCurrentItem(2, true)
                }
                R.id.menu_mine -> {
                    viewBind.viewPager.setCurrentItem(3, true)
                }
            }
            true
        }
    }

    inner class ViewPageAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = fragmentList.size

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }
}