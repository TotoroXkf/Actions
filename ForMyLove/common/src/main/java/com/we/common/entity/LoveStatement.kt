package com.we.common.entity


data class LoveStatementResponse(val results: MutableList<LoveStatement>)

data class LoveStatement(
    val objectId: String,
    val statement: String
)