package com.example.client.main

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.client.R
import com.example.client.util.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.ByteArrayOutputStream
import java.net.Socket

class MainActivity : AppCompatActivity() {
	private val handler = Handler(Looper.getMainLooper())
	private var viewModel: MainViewModel? = null
	private var view: MainView? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		view = LayoutInflater.from(this).inflate(R.layout.activity_main, null, false) as MainView?
		setContentView(view)
		init()
	}
	
	private fun init() {
		EventBus.getDefault().register(this)
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //隐藏状态栏
		view?.cameraView?.setLifecycleOwner(this)
		initViewModel()
		viewModel?.viewStateLiveData?.value = viewModel?.createInitViewState()
		handler.post(checkPermissionRunnable)
	}
	
	private fun initViewModel() {
		viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
		
		viewModel?.viewStateLiveData?.observe(this, Observer<MainViewState> { viewState ->
			view?.update(viewState)
		})
		
		viewModel?.serverIpLiveData?.observe(this, Observer<String> { ip ->
			if (TextUtils.isEmpty(ip)) {
				return@Observer
			}
			sendIpToServer(ip)
		})
		
		viewModel?.deviceNumberLiveData?.observe(this, Observer<Int> { number ->
			val viewState = viewModel?.viewStateLiveData?.value
			viewState?.let {
				it.number = number.toString()
				it.isLoading = false
				it.showCamera = true
				it.showNumber = true
			}
			viewModel?.viewStateLiveData?.value = viewState
			runCommandSocket()
		})
	}
	
	private val checkPermissionRunnable = object : Runnable {
		override fun run() {
			if (checkPermissions()) {
				handler.removeCallbacks(this)
				viewModel?.getServerIp()
			} else {
				handler.postDelayed(this, 1000)
			}
		}
	}
	
	private fun checkPermissions(): Boolean {
		if (viewModel == null) {
			return false
		}
		for (permission in PERMISSIONS) {
			if (ContextCompat.checkSelfPermission(this, permission)
				!= PackageManager.PERMISSION_GRANTED
			) {
				return false
			}
		}
		return true
	}
	
	private fun sendIpToServer(serverIp: String) {
		Thread {
			val deviceIp = getDeviceIp(this@MainActivity)
			if (TextUtils.isEmpty(deviceIp)) {
				return@Thread
			}
			val number = sendIpAndGetDeviceNumber(serverIp, deviceIp)
			handler.post {
				viewModel?.deviceNumberLiveData?.value = number
			}
		}.start()
	}
	
	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	fun dispatchCommon(socket: Socket) {
		viewModel?.commandSocket = socket
		val reader = getSocketReader(socket)
		viewModel?.commandReader = reader
		val action = reader.readLine()
		socket.shutdownInput()
		Log.e("xkf123456789", "接受到新的命令: $action")
		handler.post {
			executeCommand(action)
		}
	}
	
	/**
	 * 在主线程中
	 */
	private fun executeCommand(action: String) {
		when (action) {
			ACTION_CAPTURE -> {
				view?.cameraView?.capturePicture()
			}
			else -> {
			
			}
		}
		
	}
	
	@Subscribe(threadMode = ThreadMode.MAIN)
	fun takePicture(bytes: ByteArray) {
		val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
		val matrix = Matrix()
		matrix.postRotate(90f)
		val rotateBitmap =
			Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
		val outputStream = ByteArrayOutputStream()
		rotateBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
		val picture = outputStream.toByteArray()
		
		Thread {
			val socket = viewModel?.commandSocket
			val reader = viewModel?.commandReader
			val writer = viewModel?.commandSocket?.getOutputStream()
			writer?.write(picture)
			writer?.flush()
			socket?.shutdownOutput()
			
			reader?.close()
			writer?.close()
			socket?.close()
			
			viewModel?.commandSocket = null
			viewModel?.commandReader = null
		}.start()
		
	}
	
	override fun onDestroy() {
		EventBus.getDefault().unregister(this)
		super.onDestroy()
	}
}
