package com.example.client.main

import android.graphics.Color
import com.otaliastudios.cameraview.*
import java.io.Serializable

class CameraParameter {
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
	
	
}