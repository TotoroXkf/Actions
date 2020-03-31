package formylove.statement

class StatementEntity(val results: List<StatementData>? = emptyList()) {
    class StatementData(
        val statement: String? = "",
        val objectId: String? = ""
    )
    
    fun getObjectId(statement: String): String {
        if (results.isNullOrEmpty()) {
            return ""
        }
        results.forEach { data ->
            if (data.statement == statement) {
                return data.objectId ?: ""
            }
        }
        return ""
    }
    
    fun getStatementList(): MutableList<String> {
        val list = mutableListOf<String>()
        results?.forEach { data ->
            list.add(data.statement ?: "")
        }
        return list
    }
}

data class StatementData(
    val loveStatements: MutableList<String>
)