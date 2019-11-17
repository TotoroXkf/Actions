package com.example.formylove.statement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StatementViewModel : ViewModel() {
    val textList = MutableLiveData<MutableList<String>>()
    val model = StatementModel()
    
    fun getTextList(): MutableList<String> {
        return textList.value ?: mutableListOf()
    }
    
    suspend fun loadStatements() = GlobalScope.launch(Dispatchers.Main) {
    
    }
}