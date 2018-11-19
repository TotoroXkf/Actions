package com.example.myview.summarybar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myview.R
import kotlinx.android.synthetic.main.activity_summary_bar.*

class SummaryBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary_bar)

        summaryBarView.addBars(arrayListOf(100f, 250f, 333f, 122.5f, 111.1f), arrayListOf())
    }
}
