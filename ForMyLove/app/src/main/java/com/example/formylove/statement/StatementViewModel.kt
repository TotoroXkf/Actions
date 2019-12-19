package com.example.formylove.statement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formylove.utils.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatementViewModel : ViewModel() {
    val statementList = mutableListOf<String>()
    val deleteLiveData = MutableLiveData<Int>()
    private var statementEntity: StatementEntity? = null
    
    suspend fun loadStatements() {
        statementEntity = withContext(Dispatchers.IO) {
            val response = RetrofitHelper.getBmobService().getStatements().execute()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                null
            }
        }
        statementEntity ?: return
        statementList.clear()
        statementList.addAll(statementEntity!!.getStatementList())
    }
    
    suspend fun uploadNewStatement(text: String) = withContext(Dispatchers.IO) {
        RetrofitHelper.getBmobService().uploadStatement(StatementEntity.StatementData(text))
            .execute()
        return@withContext
    }
    
    fun deleteStatement(position: Int) {
        statementEntity ?: return
        if (position in statementList.indices) {
            val id = statementEntity!!.getObjectId(statementList[position])
            statementList.removeAt(position)
            deleteLiveData.value = position
        }
    }
}