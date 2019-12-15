package com.example.formylove.statement

import androidx.lifecycle.ViewModel
import com.example.formylove.utils.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatementViewModel : ViewModel() {
    private val model = StatementModel()
    val statementList = mutableListOf<String>()
    
    suspend fun loadStatements(): MutableList<String> {
        val statementEntity = model.getStatement()
        statementList.clear()
        statementEntity?.results?.let { statements ->
            statements.forEach { data ->
                statementList.add(data.statement ?: "")
            }
        }
        return statementList
    }
    
    suspend fun uploadNewStatement(text: String) = withContext(Dispatchers.IO) {
        RetrofitHelper.getBmobService().uploadStatement(StatementEntity.StatementData(text))
            .execute()
        return@withContext
    }
}