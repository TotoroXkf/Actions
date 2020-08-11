package com.xkf.trainingplatform.feature

import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.xkf.trainingplatform.R

class Video1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val path = "android.resource://$packageName/raw/video"
        videoView.setVideoPath(path)
        videoView.setMediaController(MediaController(this))
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }
}