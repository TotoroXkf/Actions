package com.xkf.androidtest

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingResource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.liveData.observe(this, Observer {
            val adapter = MyAdapter()
            recyclerView.adapter = adapter
        })

        mainViewModel.loadData()
        //        button.setOnClickListener {
        //        }
    }

    inner class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val textView = TextView(this@MainActivity)
            textView.setPadding(20, 40, 40, 20)
            return object : RecyclerView.ViewHolder(textView) {}
        }

        override fun getItemCount(): Int = mainViewModel.liveData.value!!.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (position == 40) {
                (holder.itemView as TextView).text = "mainViewModel.liveData.value!![position]"
            } else {
                (holder.itemView as TextView).text = mainViewModel.liveData.value!![position]
            }
        }
    }

    fun getIdlingResource(): IdlingResource {
        mainViewModel.liveData.value?.size
        return mainViewModel
    }
}
