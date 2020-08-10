package com.xkf.trainingplatform.feature.select

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xkf.trainingplatform.model.SelectDoctor
import kotlin.random.Random


class SelectDoctorViewModel : ViewModel() {
    val province = MutableLiveData("地区")
    val jobTitle = MutableLiveData("职称")
    val doctorAge = MutableLiveData("医龄")
    val hospital = MutableLiveData("所属医院")

    val provinceList = mutableListOf(
        "北京市",
        "天津市",
        "河北省",
        "山西省",
        "内蒙古自治区",
        "辽宁省",
        "吉林省",
        "黑龙江省",
        "上海市",
        "江苏省",
        "浙江省",
        "安徽省",
        "福建省",
        "江西省",
        "山东省",
        "河南省",
        "湖北省",
        "湖南省",
        "广东省",
        "广西壮族自治区",
        "海南省",
        "重庆市",
        "四川省",
        "贵州省",
        "云南省",
        "西藏自治区",
        "陕西省",
        "甘肃省",
        "青海省",
        "宁夏回族自治区",
        "新疆维吾尔自治区"
    )
    val doctorAgeList = mutableListOf("职龄1年", "职龄5年", "职龄10年", "职龄15年", "职龄20年", "职龄25年", "职龄30年")
    val jobTitleList = mutableListOf("主治医师", "副主任医师", "主任医师")
    val hospitalList = mutableListOf(
        "哈尔滨医科大学附属一院",
        "哈尔滨医科大学附属二院",
        "哈尔滨医科大学附属三院",
        "哈尔滨医科大学附属四院",
        "哈尔滨医科大学附属五院"
    )

    var allDoctorList = mutableListOf<SelectDoctor>()
    var currentDoctorList = listOf<SelectDoctor>()

    fun mockData() {
        allDoctorList.clear()
        val names = mutableListOf("张三", "李四", "王五", "马六")
        val goodAts = mutableListOf("角膜炎", "近视", "干眼", "结膜炎", "眼底检查", "飞蚊症", "视网膜脱离")
        val random = Random(System.currentTimeMillis())

        for (i in 0 until 100) {
            val selectDoctor = SelectDoctor(
                name = names[random.nextInt(0, names.size)],
                local = provinceList[random.nextInt(0, provinceList.size)],
                hospital = hospitalList[random.nextInt(0, hospitalList.size)],
                jobTitle = jobTitleList[random.nextInt(0, jobTitleList.size)],
                doctorAge = doctorAgeList[random.nextInt(0, doctorAgeList.size)],
                answerNum = "回答次数：" + random.nextInt(10, 300).toString() + "次",
                prescriptionNum = "" + "月处方数：" + random.nextInt(10, 200).toString() + "次",
                responseTime = "响应时间：" + random.nextInt(10, 100).toString() + "分钟",
                goodAt = "擅长：" +
                        goodAts[random.nextInt(0, goodAts.size / 3)] +
                        " , " +
                        goodAts[random.nextInt(goodAts.size / 3, (goodAts.size / 3) * 2)] +
                        " , " +
                        goodAts[random.nextInt((goodAts.size / 3) * 2, goodAts.size)]
            )
            allDoctorList.add(selectDoctor)
        }

        currentDoctorList = ArrayList(allDoctorList)
    }

    fun filterList() {
        currentDoctorList = allDoctorList.filter {
            if (province.value != "地区" && it.local != province.value) {
                return@filter false
            }
            if (jobTitle.value != "职称" && it.jobTitle != jobTitle.value) {
                return@filter false
            }
            if (doctorAge.value != "医龄" && it.doctorAge != doctorAge.value) {
                return@filter false
            }
            if (hospital.value != "所属医院" && it.hospital != hospital.value) {
                return@filter false
            }
            return@filter true
        }
    }
}