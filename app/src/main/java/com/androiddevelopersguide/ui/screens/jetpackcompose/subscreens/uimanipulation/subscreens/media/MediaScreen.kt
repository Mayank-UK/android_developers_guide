package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.media

import android.media.MediaPlayer
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.R
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun MediaScreen(
    navController: NavController,
    viewModel: MediaViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Media",
            )
        },
        mobileUI = {
            Audio()
            Video()
        }
    )
}

@Composable
fun Audio() {
    Section(
        title = "Audio",
        description = """
            To play audio, first create an android resource directory inside res/ named raw/ and add audio file there.
            
            The sample audio used is very low in sound, increase the volume to hear it.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current
            var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
            var isPlaying by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (isPlaying) {
                            mediaPlayer?.pause()
                        } else {
                            if (mediaPlayer == null) {
                                mediaPlayer = MediaPlayer.create(context, R.raw.sample_audio)
                            }
                            mediaPlayer?.start()
                        }
                        isPlaying = !isPlaying
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (isPlaying) "Pause" else "Play")
                }

                Button(
                    onClick = {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        isPlaying = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Stop")
                }
            }
        },
        code = """
            val context = LocalContext.current
            var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
            var isPlaying by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (isPlaying) {
                            mediaPlayer?.pause()
                        } else {
                            if (mediaPlayer == null) {
                                mediaPlayer = MediaPlayer.create(context, R.raw.sample_audio)
                            }
                            mediaPlayer?.start()
                        }
                        isPlaying = !isPlaying
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (isPlaying) "Pause" else "Play")
                }

                Button(
                    onClick = {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        isPlaying = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Stop")
                }
        """.trimIndent()
    )
}

@Composable
fun Video() {
    Section(
        title = "Video",
        description = """
            To play video, first create an android resource directory inside res/ named raw/ and add video file there.
            
            There can be 2 approaches for video, one is using inbuilt jetpack compose elements and other is using exo player, which is widely used media player for android.
            
            The inbuilt approach is used here.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current
            var videoView by remember { mutableStateOf<VideoView?>(null) }

            LaunchedEffect(context) {
                videoView?.let {
                    it.setMediaController(MediaController(context))
                    it.setVideoPath("android.resource://${context.packageName}/${R.raw.sample_video}")
                    it.start()
                }
            }

            AndroidView(
                factory = {
                    VideoView(context).apply {
                        videoView = this
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(512.dp)
            )
        },
        code = """
            val context = LocalContext.current
            var videoView by remember { mutableStateOf<VideoView?>(null) }

            LaunchedEffect(context) {
                videoView?.let {
                    it.setMediaController(MediaController(context))
                    it.setVideoPath("android.resource://$/{context.packageName}/$/{R.raw.sample_video}")
                    it.start()
                }
            }

            AndroidView(
                factory = {
                    VideoView(context).apply {
                        videoView = this
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(512.dp)
            )
        """.trimIndent()
    )
}

@Preview
@Composable
fun MediaScreenPreview() {
    MediaScreen(navController = rememberNavController())
}