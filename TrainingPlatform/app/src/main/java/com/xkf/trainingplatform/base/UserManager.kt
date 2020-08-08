package com.xkf.trainingplatform.base

const val KET_USER_NAME = "userName"
const val KEY_PASSWORD = "password"

object UserManager {
    fun isLogin(): Boolean {
        val userName = Global.sharedPreferences.getString(KET_USER_NAME, "") ?: ""
        val password = Global.sharedPreferences.getString(KEY_PASSWORD, "") ?: ""

        if (userName.isNotEmpty() && password.isNotEmpty()) {
            return true
        }

        return false
    }

    fun register(userName: String, password: String): Boolean {
        if (userName.isEmpty() || password.isEmpty()) {
            return false
        }
        val edit = Global.sharedPreferences.edit()
        edit.putString(KET_USER_NAME, userName)
        edit.putString(KEY_PASSWORD, password)
        edit.apply()
        return true
    }
}