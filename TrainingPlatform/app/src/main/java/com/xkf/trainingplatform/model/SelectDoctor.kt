package com.xkf.trainingplatform.model

data class SelectDoctor(
    val name: String,
    val local: String,
    val hospital: String,
    val jobTitle: String,
    val doctorAge: String,
    val answerNum: String,
    val prescriptionNum: String,
    val responseTime: String,
    val goodAt: String
)