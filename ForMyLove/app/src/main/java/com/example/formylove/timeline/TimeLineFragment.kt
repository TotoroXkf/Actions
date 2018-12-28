package com.example.formylove.timeline

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.FindListener
import com.example.formylove.R


class TimeLineFragment : Fragment() {
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var thingsList: RecyclerView
    private val things = ArrayList<TimeLine>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time_line, container, false)
        refreshLayout = view.findViewById(R.id.refreshLayout)
        thingsList = view.findViewById(R.id.thingsList)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        thingsList.layoutManager = linearLayoutManager

        refreshLayout.setOnRefreshListener {
            update()
        }

        update()

        return view
    }


    /*
     * 刷新数据，重新从网络读取
     */
    private fun update() {
        val query = BmobQuery<TimeLine>()
        query.findObjects(object : FindListener<TimeLine>() {
            override fun done(result: MutableList<TimeLine>?, e: BmobException?) {
                if (e == null && result != null) {
                    result.sortWith(Comparator { p1, p2 ->
                        val date1 = p1.date!!.split("-")
                        val year1 = date1[0].toInt()
                        val month1 = date1[1].toInt()
                        val day1 = date1[2].toInt()

                        val date2 = p2.date!!.split("-")
                        val year2 = date2[0].toInt()
                        val month2 = date2[1].toInt()
                        val day2 = date2[2].toInt()

                        if (year1 < year2 ||
                                year1 == year2 && month1 < month2 ||
                                year1 == year2 && month1 == month2 && day1 < day2) {
                            -1
                        } else {
                            1
                        }
                    })

                    //更新数据
                    things.clear()
                    things.addAll(result)
                    //添加适配器
                    thingsList.adapter = TimeLineAdapter()

                    refreshLayout.isRefreshing = false
                }
            }
        })

    }


    inner class TimeLineAdapter : RecyclerView.Adapter<ViewHolder>() {


        override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): ViewHolder {
            val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.time_line_item, viewGroup, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return things.size
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val itemView = viewHolder.timeLineItemView
            itemView.setData(things[position], getItemViewType(position))
        }

        override fun getItemViewType(position: Int): Int {
            return position % 2
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeLineItemView = itemView.findViewById<TimeLineItemView>(R.id.timeLineItemView)!!
    }
}


