package com.xkf.roomtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) var taskId: Int,
    var name: String,
    var desc: String,
    var subTasks: MutableList<SubTask>
)

data class SubTask(
    var name: String,
    var desc: String
)