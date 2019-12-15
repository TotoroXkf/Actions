package com.example.formylove.statement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import kotlinx.android.synthetic.main.item_statement_list.view.*


class StatementAdapter(
    private val viewModel: StatementViewModel
) : RecyclerView.Adapter<StatementViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatementViewHolder {
        return StatementViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_statement_list,
                parent,
                false
            )
        )
    }
    
    override fun getItemCount(): Int = viewModel.statementList.size
    
    override fun onBindViewHolder(holderStatement: StatementViewHolder, position: Int) {
        val text = viewModel.statementList[position]
        holderStatement.bind(text)
    }
}

class StatementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view.text
    
    fun bind(text: String) {
        textView.text = text
    }
}