package com.xkf.trainingplatform.model

import com.xkf.trainingplatform.base.*


class User {
    var userName: String = ""
        get() = Global.sharedPreferences.getString(KET_USER_NAME, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KET_USER_NAME, value)
            edit.apply()
            field = value
        }

    var password: String = ""
        get() = Global.sharedPreferences.getString(KEY_PASSWORD, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_PASSWORD, value)
            edit.apply()
            field = value
        }

    var id: String = ""
        get() = Global.sharedPreferences.getString(KEY_ID, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_ID, value)
            edit.apply()
            field = value
        }

    var isValidated: Boolean = false
        get() = (Global.sharedPreferences.getString(KEY_USER_TYPE, "") ?: "") == TYPE_USER
        set(value) {
            field = value
            val edit = Global.sharedPreferences.edit()
            if (value) {
                edit.putString(KEY_USER_TYPE, TYPE_USER)
            } else {
                edit.putString(KEY_USER_TYPE, "")
            }
            edit.apply()
        }
}