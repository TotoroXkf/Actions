package com.example.formylove.kiss

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.formylove.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*
import kotlin.collections.ArrayList


const val OBJECT_ID = "V7Ad333T"

class KissFragment : Fragment() {
    private lateinit var kissCalendar: KissCalendar
    private var needReWrite = false
    private var currentYear = 0
    private var currentMonth = 0
    private var currentDay = 0
    private val days = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cal = Calendar.getInstance()
        currentYear = cal.get(Calendar.YEAR)
        currentMonth = cal.get(Calendar.MONTH) + 1
        currentDay = cal.get(Calendar.DATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kiss, container, false)
        kissCalendar = view.findViewById(R.id.kissCalender)
        return view
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        val bmobQuery = BmobQuery<KissDay>()
        bmobQuery.getObject(OBJECT_ID, object : QueryListener<KissDay>() {
            override fun done(kissDay: KissDay?, e: BmobException?) {
                if (e == null && kissDay != null && kissDay.time != null) {
                    parseDays(kissDay.time!!)
                }
            }
        })
    }

    fun parseDays(time: String) {
        if (time.isEmpty()) {
            return
        }
        //返回字符串格式
        //2019-3@1,2,3
        val year = time.substring(0, 4).toInt()
        if (currentYear != year) {
            needReWrite = true
            return
        }
        val month = time.substring(time.indexOf("-") + 1, time.indexOf("@")).toInt()
        if (currentMonth != month) {
            needReWrite = true
            return
        }
        days.addAll(time.substring(time.indexOf("@") + 1).split(",").map { it.toInt() })
        days.sort()
        kissCalendar.setDays(days)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe
    fun onDayAdded(message: DayMessageEvent) {
        val day = message.day
        days.add(day)
        days.sort()
        val kissDay = KissDay()
        var newTime = "$currentYear-$currentMonth@"
        for (i in 0 until days.size) {
            if (i == days.size - 1) {
                newTime += "${i + 1}"
            } else {
                newTime += "${i + 1},"
            }
        }
        kissDay.time = newTime
        kissDay.update(OBJECT_ID, object : UpdateListener() {
            override fun done(e: BmobException?) {

            }
        })
    }
}