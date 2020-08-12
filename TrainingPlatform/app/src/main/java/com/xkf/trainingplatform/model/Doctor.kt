package com.xkf.trainingplatform.model

import com.xkf.trainingplatform.base.*

class Doctor {
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

    var certificatePath: String = ""
        get() = Global.sharedPreferences.getString(KEY_CERTIFICATE_PATH, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_CERTIFICATE_PATH, value)
            edit.apply()
            field = value
        }

    var avatarPath = ""
        get() = Global.sharedPreferences.getString(KEY_AVATAR_PATH, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_AVATAR_PATH, value)
            edit.apply()
            field = value
        }

    var sex = ""
        get() = Global.sharedPreferences.getString(KEY_SEX, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_SEX, value)
            edit.apply()
            field = value
        }

    var age = ""
        get() = Global.sharedPreferences.getString(KEY_AGE, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_AGE, value)
            edit.apply()
            field = value
        }

    var hospital = ""
        get() = Global.sharedPreferences.getString(KEY_HOSPITAL, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_HOSPITAL, value)
            edit.apply()
            field = value
        }

    var department = ""
        get() = Global.sharedPreferences.getString(KEY_DEPARTMENT, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_DEPARTMENT, value)
            edit.apply()
            field = value
        }

    var jobTitle = ""
        get() = Global.sharedPreferences.getString(KEY_JOB_TITLE, "") ?: ""
        set(value) {
            val edit = Global.sharedPreferences.edit()
            edit.putString(KEY_JOB_TITLE, value)
            edit.apply()
            field = value
        }

    var isValidated: Boolean = false
        get() = (Global.sharedPreferences.getString(KEY_USER_TYPE, "") ?: "") == TYPE_DOCTOR
        set(value) {
            field = value
            val edit = Global.sharedPreferences.edit()
            if (value) {
                edit.putString(KEY_USER_TYPE, TYPE_DOCTOR)
            } else {
                edit.putString(KEY_USER_TYPE, "")
            }
            edit.apply()
        }
}