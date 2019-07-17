package com.example.roomtest

import androidx.room.*

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name: String,
    var page: Int,
    var author: String
)

data class SubBook(var name: String)

@Entity(tableName = "images")
data class ImageEntity(@PrimaryKey var id: Int, @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var bytes: ByteArray)

