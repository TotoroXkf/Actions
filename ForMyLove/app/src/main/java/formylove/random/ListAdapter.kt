package formylove.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.formylove.R
import kotlinx.android.synthetic.main.item_random_thing_list.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val thingsList = mutableListOf<String>()
    private val colorList = mutableListOf<Int>()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_random_thing_list,
                parent,
                false
            )
        )
    }
    
    fun setData(newColorList: List<Int>, newThingList: List<String>) {
        thingsList.clear()
        thingsList.addAll(newThingList)
        colorList.clear()
        colorList.addAll(newColorList)
        notifyDataSetChanged()
    }
    
    override fun getItemCount(): Int = thingsList.size
    
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(color = colorList[position], thing = thingsList[position])
    }
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text: TextView = itemView.text
        private val colorImage: ImageView = itemView.color
        
        fun bind(color: Int, thing: String) {
            text.text = thing
            colorImage.setBackgroundColor(color)
        }
    }
}