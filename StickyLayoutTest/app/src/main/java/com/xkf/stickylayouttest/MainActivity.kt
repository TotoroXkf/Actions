package com.xkf.stickylayouttest

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val itemView = TextView(this@MainActivity)
                val layoutParam =
                    RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 600)
                itemView.layoutParams = layoutParam
                return object : RecyclerView.ViewHolder(itemView) {}
            }

            override fun getItemCount(): Int = 1000

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                (holder.itemView as TextView).text = position.toString()
            }
        }
    }
}