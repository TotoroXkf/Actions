package com.xkf.databindingtest

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton

class NoDataBindingActivity : AppCompatActivity() {
    private lateinit var textContent: TextView
    private lateinit var textTimes: TextView
    private lateinit var textView: TextView
    private lateinit var addButton: MaterialButton
    private lateinit var deleteButton: MaterialButton
    private lateinit var changeTextButton: MaterialButton

    private val viewModel by lazy {
        ViewModelProvider(this).get(NoDataBindingViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_data_binding)

        textContent = findViewById(R.id.textContent)
        textTimes = findViewById(R.id.textTimes)
        textView = findViewById(R.id.textView)
        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)
        changeTextButton = findViewById(R.id.changeTextButton)

        renderUI()

        addButton.setOnClickListener {
            viewModel.onAdd()
        }

        deleteButton.setOnClickListener {
            viewModel.onDelete()
        }

        changeTextButton.setOnClickListener {
            viewModel.onChangeText()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderUI() {
        addButton.setBackgroundColor(Color.parseColor(viewModel.addButtonColor))
        deleteButton.setBackgroundColor(Color.parseColor(viewModel.deleteButtonColor))
        textTimes.text = "x" + viewModel.text
        textContent.text = viewModel.content
    }
}