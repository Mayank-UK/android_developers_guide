package com.androiddevelopersguide.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.androiddevelopersguide.R

class PlaySoundService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Release any existing instance before creating a new one to avoid leaks.
        mediaPlayer?.release()
        // MediaPlayer.create(...) can return null if the resource cannot be decoded;
        // bail safely rather than NPE-ing on .start().
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio)?.also { it.start() }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
