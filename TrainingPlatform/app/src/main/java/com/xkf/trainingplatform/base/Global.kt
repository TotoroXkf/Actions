package com.xkf.trainingplatform.base

import android.app.Activity
import android.content.SharedPreferences
import com.xkf.trainingplatform.model.Doctor
import com.xkf.trainingplatform.model.User


const val KET_USER_NAME = "userName"
const val KEY_PASSWORD = "password"
const val KEY_ID = "id"
const val KEY_USER_TYPE = "type"
const val KEY_AVATAR_PATH = "avatarPath"
const val KEY_SEX = "sex"
const val KEY_AGE = "age"
const val KEY_HOSPITAL = "hospital"
const val KEY_DEPARTMENT = "department"
const val KEY_JOB_TITLE = "jobTitle"
const val KEY_CERTIFICATE_PATH = "certificatePath"

const val TYPE_USER = "user"
const val TYPE_DOCTOR = "doctor"

object Global {
    lateinit var application: MyApplication
    lateinit var sharedPreferences: SharedPreferences

    lateinit var user: User
    lateinit var doctor: Doctor

    fun init(application: MyApplication) {
        Global.application = application
        sharedPreferences = application.getSharedPreferences("app", Activity.MODE_PRIVATE)
        user = User()
        doctor = Doctor()
    }

    fun isLogin(): Boolean {
        return user.isValidated || doctor.isValidated
    }

    fun isUser(): Boolean {
        return user.isValidated
    }

    fun isDoctor(): Boolean {
        return doctor.isValidated
    }

    fun registerUser(userName: String, password: String, id: String) {
        if (userName.isNotEmpty()) {
            user.userName = userName
        }
        if (password.isNotEmpty()) {
            user.password = password
        }
        if (id.isNotEmpty()) {
            user.id = id
        }
        user.isValidated = true
    }

    fun registerDoctor(userName: String, password: String, id: String) {
        if (userName.isNotEmpty()) {
            doctor.userName = userName
        }
        if (password.isNotEmpty()) {
            doctor.password = password
        }
        if (id.isNotEmpty()) {
            doctor.id = id
        }
        doctor.isValidated = true
    }
}