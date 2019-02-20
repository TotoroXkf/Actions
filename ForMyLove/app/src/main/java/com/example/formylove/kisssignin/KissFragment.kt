package com.example.formylove.kisssignin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formylove.R

class KissFragment : Fragment() {
	private lateinit var kissCalendar: KissCalendar
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_kiss, container, false)
		kissCalendar = view.findViewById(R.id.kissCalender)
		kissCalendar.setDays(arrayListOf(1, 3, 6, 10, 12, 2))
		return view
	}
}