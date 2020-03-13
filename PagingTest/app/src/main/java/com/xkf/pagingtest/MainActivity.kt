package com.xkf.pagingtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_loading.view.*
import kotlinx.android.synthetic.main.layout_item.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView的使用和往常的一样
        val adapter = MyAdapter(MyDiffCallback())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.pageList.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.endLiveData.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }

    // 继承更加
    inner class MyAdapter(diffCallback: DiffUtil.ItemCallback<MyModel>) :
        PagedListAdapter<MyModel, RecyclerView.ViewHolder>(diffCallback) {

        override fun getItemViewType(position: Int): Int {
            if (position == itemCount - 1) {
                return -1
            }
            return 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            if (viewType == -1) {
                return LoadingViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_loading, parent, false)
                )
            }
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is MyViewHolder) {
                holder.bind(getItem(position)!!)
            } else if (holder is LoadingViewHolder) {
                holder.bind()
            }
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.textView
        private val checkBox = itemView.checkedBox

        fun bind(model: MyModel) {
            textView.text = model.name
            checkBox.isChecked = model.finished
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.textEnd
        private val progressBar = itemView.progressBar

        fun bind() {
            if (viewModel.endLiveData.value!!) {
                textView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            } else {
                textView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<MyModel>() {
        override fun areItemsTheSame(oldItem: MyModel, newItem: MyModel): Boolean =
            oldItem == newItem


        override fun areContentsTheSame(oldItem: MyModel, newItem: MyModel): Boolean =
            oldItem.name == newItem.name
    }
}
