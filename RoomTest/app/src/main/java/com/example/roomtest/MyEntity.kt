package com.example.roomtest

import androidx.room.*

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var page: Int,
    var author: String
)

