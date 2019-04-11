package com.example.client.main

import android.Manifest

val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

const val IP_COLLECTOR_PORT = 12306
const val COMMAND_PORT = 12307

/**
 * 查看当前连接的设备
 */
const val ACTION_DEVICE_COUNT = "count"
/**
 * 拍摄
 */
const val ACTION_CAPTURE = "capture"
/**
 * 断开连接
 */
const val ACTION_FINISH = "finish"
/**
 * 回文测试
 */
const val ACTION_ECHO = "echo"
/**
 * 测量命令从发出到接收的延迟
 */
const val ACTION_DELAY_TEST = "delay"