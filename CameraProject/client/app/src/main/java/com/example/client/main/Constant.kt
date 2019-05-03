package com.example.client.main

import android.Manifest

val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

const val SOCKET_PORT = 12306
const val OK = "ok"
const val ERROR = "error"
const val FILE_NAME = "picture.jpg"
const val CAMERA_PARAMETER = "camera_parameter"

//命令列表-------------------------------------------------------------------------------------------

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

/**
 * 服务端删除指定的注册的手机
 */
const val ACTION_REMOVE = "remove"

/**
 * 传输拍摄的照片到服务端
 */
const val ACTION_GET = "get"

/**
 * 参数设置，控制焦距
 */
const val ACTION_ZOOM = "zoom"

/**
 * 参数设置，控制hdr
 */
const val ACTION_HDR = "hdr"

/**
 * 参数设置，控制flash
 */
const val ACTION_FLASH = "flash"

/**
 * 参数设置，控制白平衡
 */
const val ACTION_WHITE_BALANCE = "white_balance"

//命令列表-------------------------------------------------------------------------------------------


//参数列表-------------------------------------------------------------------------------------------

const val FLASH = "flash"
val FLASH_STRING = arrayOf("off", "on", "auto", "torch")

const val HDR = "hdr"
val HDR_STRING = arrayOf("off", "on")

const val WHITE_BALANCE = "white_balance"
val WHITE_BALANCE_STRING = arrayOf("auto", "incandescent", "fluorescent,", "daylight", "cloudy")

const val ZOOM = "zoom"

//参数列表-------------------------------------------------------------------------------------------
