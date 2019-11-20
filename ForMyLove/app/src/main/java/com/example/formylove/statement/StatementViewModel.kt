package com.example.formylove.statement

import androidx.lifecycle.ViewModel

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
}