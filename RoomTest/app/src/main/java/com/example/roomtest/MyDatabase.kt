package com.example.roomtest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getBookDao(): MyDao
}