package com.example.lab6video
//http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4

// http://stream.whus.org:8000/whusfm

import android.content.res.Configuration
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var videoButton: Button
    private lateinit var radioButton: Button
    private lateinit var nextVideoButton: Button
    private lateinit var nextRadioButton: Button
    private var videoPlayer: MediaPlayer? = null
    private var radioPlayer: MediaPlayer? = null
    private var currentVideoIndex = 0
    private var currentRadioIndex = 0
    private val videoUrls = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"
    )

    private val radioUrls = listOf(
        "http://stream.whus.org:8000/whusfm",
        "https://stream.bigfm.de/hiphop/mp3-128/",
        "https://server.webnetradio.net/proxy/wsjf",
        "http://146.71.118.220:35025/autodj"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        videoView.visibility = View.VISIBLE

        videoButton = findViewById(R.id.videoButton)
        radioButton = findViewById(R.id.radioButton)
        nextVideoButton = findViewById(R.id.nextVideoButton)
        nextRadioButton = findViewById(R.id.nextRadioButton) // Initialize nextRadioButton

        // Set the first video and radio URLs
        setVideoUri(videoUrls[currentVideoIndex])
        setRadioUri(radioUrls[currentRadioIndex])

        videoButton.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                videoButton.text = "Play Video"
            } else {
                videoView.start()
                videoButton.text = "Pause Video"
            }
        }

        nextVideoButton.setOnClickListener {
            currentVideoIndex = (currentVideoIndex + 1) % videoUrls.size
            setVideoUri(videoUrls[currentVideoIndex])
        }

        radioButton.setOnClickListener {
            if (radioPlayer == null) {
                val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
                val result = audioManager.requestAudioFocus(
                    null,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN
                )

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    radioPlayer = MediaPlayer().apply {
                        setDataSource(radioUrls[currentRadioIndex])
                        prepare()
                        start()
                    }
                    radioButton.text = "Stop Radio"
                }
            } else {
                radioPlayer?.release()
                radioPlayer = null

                val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
                audioManager.abandonAudioFocus(null)

                radioButton.text = "Play Radio"
            }
        }

        nextRadioButton.setOnClickListener {
            currentRadioIndex = (currentRadioIndex + 1) % radioUrls.size
            setRadioUri(radioUrls[currentRadioIndex])
        }
    }

    private fun setVideoUri(uri: String) {
        videoView.setVideoURI(Uri.parse(uri))
        videoView.start()
    }

    private fun setRadioUri(uri: String) {
        if (radioPlayer != null) {
            radioPlayer?.release()
            radioPlayer = null
            val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
            audioManager.abandonAudioFocus(null)
            radioButton.text = "Play Radio"
        }
        radioPlayer = MediaPlayer().apply {
            setDataSource(uri)
            prepare()
            start()
        }
        radioButton.text = "Stop Radio"
    }
}


