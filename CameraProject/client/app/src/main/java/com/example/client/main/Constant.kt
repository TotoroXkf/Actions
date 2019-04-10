package com.example.client.main

import android.Manifest

//const val CAMERA_STATUS_NONE = -1
//const val CAMERA_STATUS_PREVIEW = 1
//const val CAMERA_STATUS_CAPTURING = 2

val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

const val IP_COLLECTOR_PORT = 12306
const val COMMAND_PORT = 12307

const val ACTION_CAPTURE = "capture"