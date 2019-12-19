package com.example.formylove.statement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.formylove.utils.RetrofitHelper
import com.example.formylove.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StatementViewModel : ViewModel() {
    val statementList = mutableListOf<String>()
    val deleteLiveData = MutableLiveData<Int>()
    val dialogLiveData = MutableLiveData<Boolean>()
    
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
    
    suspend fun uploadNewStatement(text: String) {
        dialogLiveData.value = true
        val success = withContext(Dispatchers.IO) {
            val data = StatementEntity.StatementData(
                statement = text,
                objectId = null
            )
            val response = RetrofitHelper.getBmobService()
                .uploadStatement(data)
                .execute()
            response.isSuccessful
        }
        dialogLiveData.value = false
        if (success) {
            toast("上传成功~~")
        } else {
            toast("上传失败~~")
        }
    }
    
    fun deleteStatement(position: Int) {
        statementEntity ?: return
        if (position !in statementList.indices) {
            return
        }
        GlobalScope.launch(Dispatchers.Main) {
            val id = statementEntity!!.getObjectId(statementList[position])
            dialogLiveData.value = true
            val success = withContext(Dispatchers.IO) {
                val response = RetrofitHelper.getBmobService().deleteStatement(id).execute()
                response.isSuccessful
            }
            dialogLiveData.value = false
            if (success) {
                toast("删除成功~~")
                statementList.removeAt(position)
                deleteLiveData.value = position
            } else {
                toast("删除失败~~")
            }
        }
    }
}