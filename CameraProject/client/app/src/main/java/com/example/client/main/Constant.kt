package com.example.client.main

import android.Manifest
import com.otaliastudios.cameraview.Hdr

val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

const val SOCKET_PORT = 12306
const val OK = "ok"
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

//命令列表-------------------------------------------------------------------------------------------

//参数列表-------------------------------------------------------------------------------------------

const val FLASH = "flash"


const val HDR = "hdr"
const val HDR_OFF = "off"
const val HDR_ON = "on"
val HDR_MAP = hashMapOf(
	Hdr.ON to HDR_ON,
	Hdr.OFF to HDR_OFF
)
val HDR_PARSE_MAP = hashMapOf(
	HDR_ON to Hdr.ON,
	HDR_OFF to Hdr.OFF
)

const val WHITE_BALANCE = "white_balance"


//参数列表-------------------------------------------------------------------------------------------
