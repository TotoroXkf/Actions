package com.xkf.trainingplatform.main

import androidx.lifecycle.ViewModel
import com.xkf.trainingplatform.base.Global

class WorkViewModel : ViewModel() {
    val title: String
        get() {
            return if (Global.isDoctor()) {
                "医生 " + Global.doctor.userName
            } else {
                "患者 " + Global.user.userName
            }
        }
    val boardIconList = arrayListOf<Int>()
    val boardTitleList = arrayListOf<String>()

    init {
        if (Global.isDoctor()) {
            boardTitleList.add("录入患者")
            boardTitleList.add("治疗安排")
            boardTitleList.add("治疗统计")
            boardTitleList.add("医生建议")
            boardTitleList.add("随访·效果")
            boardTitleList.add("")
        } else if (Global.isUser()) {
            boardTitleList.add("治疗安排")
            boardTitleList.add("模拟功能训练")
            boardTitleList.add("医生建议")
            boardTitleList.add("随访·效果")
            boardTitleList.add("")
            boardTitleList.add("")
        }
    }
}