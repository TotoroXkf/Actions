package com.xkf.exoplayertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.util.Util


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoUrl = "https://pipijoke.oss-cn-hangzhou.aliyuncs.com/6831909366883424515.mp4"
        val player = SimpleExoPlayer.Builder(this).build()

        val dataSourceFactory = CacheDataSourceFactory(this, Util.getUserAgent(this, "ExoPlayer"))
        ProgressiveMediaSource.Factory()
    }
}