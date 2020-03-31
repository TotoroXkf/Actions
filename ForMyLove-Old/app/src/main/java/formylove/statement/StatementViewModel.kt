package formylove.statement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import formylove.base.STATEMENT_JSON_RUL
import formylove.utils.GithubHelper
import formylove.utils.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatementViewModel : ViewModel() {
    private var statementData: StatementData? = null
    val statementList: MutableList<String>
        get() = statementData?.loveStatements ?: mutableListOf()
    
    val deleteLiveData = MutableLiveData<Int>()
    val dialogLiveData = MutableLiveData<Boolean>()
    
    suspend fun loadStatements() {
        statementData = withContext(Dispatchers.IO) {
            GithubHelper.parseToObject(STATEMENT_JSON_RUL, StatementData::class.java)
        }
    }
    
    suspend fun uploadNewStatement(text: String) {
        statementData ?: return
        dialogLiveData.value = true
        statementList.add(text)
        val success = withContext(Dispatchers.IO) {
            GithubHelper.updateContent(STATEMENT_JSON_RUL, statementData)
        }
        dialogLiveData.value = false
        if (success) {
            toast("上传成功~~")
        } else {
            toast("上传失败~~")
        }
    }
    
    suspend fun deleteStatement(position: Int) {
        statementData ?: return
        if (position !in statementList.indices) {
            return
        }
        dialogLiveData.value = true
        statementList.removeAt(position)
        val success = withContext(Dispatchers.IO) {
            GithubHelper.updateContent(STATEMENT_JSON_RUL, statementData)
        }
        dialogLiveData.value = false
        if (success) {
            toast("删除成功~~")
            deleteLiveData.value = position
        } else {
            toast("删除失败~~")
        }
    }
}