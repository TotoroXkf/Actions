package com.xkf.superrecyclerview

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.xkf.base.ItemViewCreator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewCreator = object : ItemViewCreator<HeadView> {
            override fun getView(parent: ViewGroup): HeadView {
                return HeadView(parent.context)
            }
        }
    }
}
