package com.we.formylove.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.we.common.component.CommonHandler
import com.we.formylove.ScaleInTransformer
import com.we.formylove.databinding.FragmentMainBinding
import com.we.formylove.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewBinding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        Log.e("xkf", viewModel.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? CommonHandler)?.cancelFullScreen()

        viewBinding = FragmentMainBinding.inflate(layoutInflater)
        viewBinding.viewModel = viewModel
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = viewModel.tabNameList.size

            override fun createFragment(position: Int): Fragment {
                return MainPageFragment(viewModel, position)
            }
        }
        viewBinding.viewPager.setPageTransformer(ScaleInTransformer())

        TabLayoutMediator(
            viewBinding.tabs,
            viewBinding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = viewModel.tabNameList[position]
        }.attach()
    }
}
