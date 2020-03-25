package com.xkf.roomtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AppContext.dataBase

        btnInsert.setOnClickListener {
            runBackground {
                val subTaskList = mutableListOf<SubTask>()
                for (i in 0 until 10) {
                    val subTask = SubTask("这是第${i}个子任务", "这是第${i}个子任务描述")
                    subTaskList.add(subTask)
                }
                val task = Task(0, "任务", "任务描述", subTaskList)
                database.taskDao().insertTask(task)
            }
        }

        btnQuery.setOnClickListener {
            runBackground {
                val list = database.taskDao().getAllTask()
                for (task in list) {
                    Log.e("xkf", task.toString())
                }
            }
        }

        btnUpdate.setOnClickListener {
            runBackground {
                val task = database.taskDao().getTaskById(1)
                task?.let {
                    it.subTasks[0].name = "这个子任务被我修改了"
                    database.taskDao().updateSubTask(it)
                }
            }
        }

        btnDelete.setOnClickListener {
            runBackground {
                val task = database.taskDao().getTaskById(1)
                task?.let {
                    database.taskDao().deleteSubTask(it)
                }
            }
        }
    }

    private fun runBackground(codeBlock: () -> Unit) {
        Thread {
            codeBlock.invoke()
        }.start()
    }
}
