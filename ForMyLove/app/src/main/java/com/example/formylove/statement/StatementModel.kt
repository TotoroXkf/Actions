package com.example.formylove.statement

import com.example.formylove.utils.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatementModel {
    suspend fun getStatement(): StatementEntity? = withContext(Dispatchers.IO) {
        val response = RetrofitHelper.getBmobService().getStatements().execute()
        if (response.isSuccessful) {
            response.body()!!
        } else {
            null
        }
    }
}