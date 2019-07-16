package com.example.navtest


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController


class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("xkf", "create")
        Log.e("xkf", this.toString())
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("xkf", "view created")
        Log.e("xkf", this.toString())
        val button = view.findViewById<Button>(R.id.go_to_second)
        val text = view.findViewById<TextView>(R.id.text_target)
        button.setOnClickListener {
            text.text = "被标记，不是新的fragment"
            val bundle = Bundle()
            bundle.putInt("myArg", 10)
            it.findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("xkf", "destroy")
    }
}
