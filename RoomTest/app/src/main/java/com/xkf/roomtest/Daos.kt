package com.xkf.roomtest

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg user: User)

    @Insert
    fun insertBothUsers(user1: User, user2: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    fun loadAllUser(): List<User>

    @Query("SELECT * FROM users WHERE age > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): List<User>

    @Query("SELECT * FROM users WHERE age BETWEEN :minAge AND :maxAge")
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): List<User>

    @Query("SELECT * FROM users WHERE first_name LIKE :searchName OR last_name LIKE :searchName")
    fun findWithName(searchName: String): List<User>

    @Query("SELECT first_name,last_name FROM users")
    fun loadNames(): List<NameTuple>
}