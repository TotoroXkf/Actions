package com.example.roomtest

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MyDao {
    @Insert
    fun insertBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("select * from book")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("select * from book where id = :id")
    fun getBookById(id: Int): Book

    @Query("select name from book")
    fun getBookByColumnName(): List<SubBook>
}