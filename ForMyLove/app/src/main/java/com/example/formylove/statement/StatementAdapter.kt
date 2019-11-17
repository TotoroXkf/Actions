package com.example.formylove.statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import kotlinx.android.synthetic.main.item_statement_list.view.*

class StatementAdapter(
    private val viewModel: StatementViewModel
) : RecyclerView.Adapter<StatementAdapter.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_statement_list,
                parent,
                false
            )
        )
    }
    
    override fun getItemCount(): Int = viewModel.getTextList().size
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = viewModel.getTextList()[position]
        holder.bind(text)
    }
    
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.text
        
        fun bind(text: String) {
            textView.text = text
        }
    }
}