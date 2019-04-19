package com.example.client.main

import android.Manifest

val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

const val SOCKET_PORT = 12306
const val OK = "ok"

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
const val ACTION_DELAY_TEST = "rtt"