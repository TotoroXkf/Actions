package com.we.formylove.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.we.common.component.CommonHandler
import com.we.common.component.CommonUtils
import com.we.common.component.ScaleInTransformer
import com.we.formylove.databinding.FragmentMainBinding
import com.we.formylove.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.mainPageList.observe(this, Observer {
            setUpViews()
        })
        viewModel.loadMainPageData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? CommonHandler)?.cancelFullScreen()
        viewBinding = FragmentMainBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    private fun setUpViews() {
        setUpViewPager()

        TabLayoutMediator(
            viewBinding.tabs,
            viewBinding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = viewModel.getMainPageList()[position].title
        }.attach()
    }

    private fun setUpViewPager() {
        // 设置一屏多页
        viewBinding.viewPager.apply {
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                val padding = CommonUtils.dpToPx(40f)
                setPadding(padding.toInt(), 0, padding.toInt(), 0)
                clipToPadding = false
            }
        }
        // 设置adapter
        viewBinding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = viewModel.getMainPageList().size

            override fun createFragment(position: Int): Fragment {
                return MainPageFragment(viewModel, position)
            }
        }
        // 设置翻页效果
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(ScaleInTransformer())
        compositePageTransformer.addTransformer(
            MarginPageTransformer(
                CommonUtils.dpToPx(20f).toInt()
            )
        )
        viewBinding.viewPager.setPageTransformer(compositePageTransformer)
    }
}
