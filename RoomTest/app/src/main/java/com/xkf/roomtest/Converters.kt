package com.xkf.roomtest

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun stringToSubTaskList(string: String?): MutableList<SubTask>? {
        val listType = object : TypeToken<MutableList<SubTask>>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun subTaskListToString(list: MutableList<SubTask>): String? {
        return Gson().toJson(list)
    }
}