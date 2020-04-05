package com.we.lovestatement.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.we.common.component.RetrofitManager
import com.we.common.entity.LoveStatement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoveStatementViewModel : ViewModel() {
    val loveStatementList = MutableLiveData(mutableListOf<LoveStatement>())

    fun loadStatementList() = viewModelScope.launch {
        val response = withContext(Dispatchers.IO) {
            val result = RetrofitManager.bmobService.getLoveStatementData().execute()
            if (result.isSuccessful) {
                return@withContext result.body()
            } else {
                return@withContext null
            }
        }
        response?.let {
            loveStatementList.value = it.results
        }
    }

    fun getLoveStatementList(): MutableList<LoveStatement> {
        return loveStatementList.value!!
    }
}