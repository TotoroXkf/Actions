package com.xkf.viewpager2test

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment(private val index: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("xkf", "第" + index + "被创建了")
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text.text = "这是第" + index.toString() + "页"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // viewPager2的缓存数量在3页左右，走到这里还会继续走销毁Fragment
        Log.e("xkf", "第$index view 被销毁了")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.e("xkf", "第" + index + "释放了")
    }
}
