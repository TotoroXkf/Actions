package com.xkf.trainingplatform.main.doctor

import androidx.lifecycle.ViewModel
import com.xkf.trainingplatform.model.ChatMessage


class DoctorMessageViewModel : ViewModel() {
    val messageList = mutableListOf<ChatMessage>()

    fun mockData() {
        messageList.apply {
            add(
                ChatMessage(
                    message = "医生，孩子遮盖后觉得看不清，不喜欢遮盖怎么办？",
                    type = 0
                )
            )
            add(
                ChatMessage(
                    message = "是正常现象，需要家长监督和鼓励，可以鼓励孩子遮盖后做一些精细的游戏，如串珠子、画图、挑豆子、拼拼图、拼乐高玩具，看绘本等，如果遮盖好，可以适当看一些动画片在手机上玩一些益智小游戏，对患者均有好处，没有坏处。",
                    type = 1
                )
            )
            add(
                ChatMessage(
                    message = "谢谢医生，没什么问题了",
                    type = 0
                )
            )
            add(
                ChatMessage(
                    message = "没事哒，有什么问题及时问",
                    type = 1
                )
            )
        }
    }
}