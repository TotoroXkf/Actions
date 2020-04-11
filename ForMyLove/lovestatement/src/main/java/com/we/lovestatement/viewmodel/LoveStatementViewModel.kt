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
            RetrofitManager.bmobService.getLoveStatementData().execute()
        }
        if (response.isSuccessful) {
            loveStatementList.value = response.body()?.results ?: mutableListOf()
        }
    }

    fun deleteStatement(index: Int) = viewModelScope.launch {
        val objectId = getLoveStatementList()[index].objectId
        objectId?.let {
            val response = withContext(Dispatchers.IO) {
                RetrofitManager.bmobService.deleteStatement(objectId).execute()
            }
            if (response.isSuccessful) {
                val list = getLoveStatementList()
                list.removeAt(index)
                loveStatementList.value = list
            }
        }
    }

    fun updateStatement(index: Int, text: String) = viewModelScope.launch {
        val list = getLoveStatementList()
        val statement = list[index]
        val objectId = statement.objectId
        objectId?.let {
            val updateStatement = LoveStatement(objectId = null, statement = text)
            val response = withContext(Dispatchers.IO) {
                RetrofitManager.bmobService.updateStatement(objectId, updateStatement).execute()
            }
            if (response.isSuccessful) {
                list.set(index, updateStatement)
                loveStatementList.value = list
            }
        }
    }

    fun addStatement(text: String) = viewModelScope.launch {
        val statement = LoveStatement(objectId = null, statement = text)
        val response = withContext(Dispatchers.IO) {
            RetrofitManager.bmobService.addNewStatement(statement).execute()
        }
        if (response.isSuccessful) {
            loadStatementList()
        }
    }

    fun getLoveStatementList(): MutableList<LoveStatement> {
        return loveStatementList.value!!
    }
}