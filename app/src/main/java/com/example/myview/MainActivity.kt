package com.example.myview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.myview.bezier.BezierActivity
import com.example.myview.summarybar.SummaryBarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewList = arrayListOf(
            "柱状统计图",
            "塞尔曲线控制"
        )
        val activityList = arrayListOf(
            SummaryBarActivity::class.java,
            BezierActivity::class.java
        )

        listView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, viewList)
        listView.setOnItemClickListener { _: AdapterView<*>, _: View, i: Int, _: Long ->
            val intent = Intent(this, activityList[i])
            startActivity(intent)
        }
    }
}
