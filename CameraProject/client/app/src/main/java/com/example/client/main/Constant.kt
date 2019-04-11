package com.example.client.main

import android.Manifest

val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

const val IP_COLLECTOR_PORT = 12306
const val COMMAND_PORT = 12307

const val ACTION_DEVICE_COUNT = "device_count"
const val ACTION_CAPTURE = "capture"
const val ACTION_FINISH = "finish"
const val ACTION_ECHO = "echo"
const val ACTION_TIME_TEST = "time"