package com.xkf.roomtest

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): List<Task>

    @Query("SELECT * FROM task WHERE taskId = :taskId")
    fun getTaskById(taskId: Int): Task?

    @Update
    fun updateSubTask(task: Task)

    @Delete
    fun deleteSubTask(task: Task)

    @Insert
    fun insertTask(task: Task)
}