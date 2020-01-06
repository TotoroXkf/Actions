package formylove.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import kotlinx.android.synthetic.main.item_main_list.view.*

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_main_list,
                parent,
                false
            )
        )
    }
    
    override fun getItemCount(): Int = MainConfigure.itemNames.size
    
    override fun onBindViewHolder(viewHolder: MainViewHolder, position: Int) {
        viewHolder.bind(MainConfigure.itemNames[position], MainConfigure.itemIcons[position])
        viewHolder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, MainConfigure.router[position]))
        }
    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(text: String, icon: Int) {
        itemView.tvText.text = text
        itemView.ivIcon.setImageResource(icon)
    }
}