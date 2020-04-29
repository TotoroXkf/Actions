package com.we.androidtest

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = Adapter()
    }

    class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val textView = TextView(parent.context)
            textView.setPadding(20, 20, 0, 20)
            return object : RecyclerView.ViewHolder(textView) {}
        }

        override fun getItemCount(): Int = 100

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder.itemView as TextView).text = position.toString()
        }
    }
}
