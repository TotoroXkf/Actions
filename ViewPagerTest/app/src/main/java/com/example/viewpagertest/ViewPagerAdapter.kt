package com.example.viewpagertest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (position == 1) {
            val view = LayoutInflater.from(container.context)
                .inflate(R.layout.fragment_blank, container, false);
            container.addView(view)
            return view
        } else {
            val recyclerView =  RecyclerView(container.context)
            recyclerView.layoutManager = LinearLayoutManager(container.context,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = RecycleViewAdapter()
            container.addView(recyclerView)
            return recyclerView;
        }
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj;
    }

    override fun getCount(): Int {
        return 2;
    }
}