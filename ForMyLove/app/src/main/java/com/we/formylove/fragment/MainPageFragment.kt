package com.we.formylove.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.we.formylove.R
import com.we.formylove.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main_page.*


class MainPageFragment(val viewModel: MainViewModel, val index: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (index % 2 == 0) {
            textView.setBackgroundColor(Color.YELLOW)
        } else {
            textView.setBackgroundColor(Color.GREEN)
        }
    }
}
