package com.example.client.main

import android.content.SharedPreferences
import android.util.Log
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
	var zoomValue = 0.0f
	var focusX = 0f
	var focusY = 0f
	var exposureCorrectionValue = 0f
	
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
	
	fun parseHdr(hdrValue: String): Boolean {
		if (hdrValue !in hdrParseMap) {
			Log.e("xkf123456789", "设置参数失败")
			return false
		}
		hdr = hdrParseMap[hdrValue]!!
		return true
	}
	
	fun parseFlash(flashValue: String): Boolean {
		if (flashValue !in flashParseMap) {
			Log.e("xkf123456789", "设置参数失败")
			return false
		}
		flash = flashParseMap[flashValue]!!
		return true
	}
	
	fun parseWhiteBalance(whiteBalanceValue: String): Boolean {
		if (whiteBalanceValue !in whiteBalanceParseMap) {
			Log.e("xkf123456789", "设置参数失败")
			return false
		}
		whiteBalance = whiteBalanceParseMap[whiteBalanceValue]!!
		return true
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
				ZOOM -> {
					zoomValue = value as Float
				}
				FOCUS_Y -> {
					focusY = value as Float
				}
				FOCUS_X -> {
					focusX = value as Float
				}
				EXPOSURE_CORRECTION -> {
					exposureCorrectionValue = value as Float
				}
			}
		}
	}
	
	fun writeParameterToLocal(editor: SharedPreferences.Editor) {
		editor.putString(FLASH, flashMap[flash])
		editor.putString(HDR, hdrMap[hdr])
		editor.putString(WHITE_BALANCE, whiteBalanceMap[whiteBalance])
		editor.putFloat(ZOOM, zoomValue)
		editor.putFloat(EXPOSURE_CORRECTION, exposureCorrectionValue)
		editor.putFloat(FOCUS_X, focusX)
		editor.putFloat(FOCUS_Y, focusY)
	}
}