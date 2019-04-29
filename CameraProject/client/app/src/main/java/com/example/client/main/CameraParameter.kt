package com.example.client.main

import android.content.SharedPreferences
import android.graphics.Color
import com.otaliastudios.cameraview.*

class CameraParameter {
	private val flashMap = HashMap<Flash, String>()
	private val flashParseMap = HashMap<String, Flash>()
	private val hdrMap = HashMap<Hdr, String>()
	private val hdrParseMap = HashMap<String, Hdr>()
	private val whiteBalanceMap = HashMap<WhiteBalance, String>()
	private val whiteBalanceParseMap = HashMap<String, WhiteBalance>()
	
	var flash = Flash.OFF
	var whiteBalance = WhiteBalance.AUTO
	var hdr = Hdr.OFF
	var audio = Audio.OFF
	var gestures = hashMapOf(
		Gesture.PINCH to GestureAction.ZOOM,
		Gesture.TAP to GestureAction.FOCUS_WITH_MARKER,
		Gesture.LONG_TAP to GestureAction.CAPTURE
	)
	var playSound = false
	var gridLine = Grid.OFF
	var gridLineColor = Color.WHITE
	var autoFocusResetDelay = 1500
	
	init {
		val flashEnum = Flash.values()
		for (i in FLASH_STRING.indices) {
			flashMap[flashEnum[i]] = FLASH_STRING[i]
			flashParseMap[FLASH_STRING[i]] = flashEnum[i]
		}
		
		val hdrEnum = Hdr.values()
		for (i in HDR_STRING.indices) {
			hdrMap[hdrEnum[i]] = HDR_STRING[i]
			hdrParseMap[HDR_STRING[i]] = hdrEnum[i]
		}
		
		val whiteBalanceEnum = WhiteBalance.values()
		for (i in WHITE_BALANCE_STRING.indices) {
			whiteBalanceMap[whiteBalanceEnum[i]] = WHITE_BALANCE_STRING[i]
			whiteBalanceParseMap[WHITE_BALANCE_STRING[i]] = whiteBalanceEnum[i]
		}
	}
	
	fun parse(parameter: Map<String, *>) {
		for ((key, value) in parameter) {
			when (key) {
				FLASH -> {
					flash = flashParseMap[value]!!
				}
				HDR -> {
					hdr = hdrParseMap[value]!!
				}
				WHITE_BALANCE -> {
					whiteBalance = whiteBalanceParseMap[value]!!
				}
			}
		}
	}
	
	fun writeParameterToLocal(editor: SharedPreferences.Editor) {
		editor.putString(FLASH, flashMap[flash])
		editor.putString(HDR, hdrMap[hdr])
		editor.putString(WHITE_BALANCE, whiteBalanceMap[whiteBalance])
	}
}