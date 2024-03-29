package com.example.k2022_03_09_radio

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    private lateinit var buttonPlay: Button
    private lateinit var buttonStop: Button
    private lateinit var buttonSwitch: Button
    private lateinit var container: LinearLayout
    private lateinit var mediaPlayer: MediaPlayer
    private var radioOn: Boolean = false
    private var currentStationIndex = 0

    private val radioStations = listOf(
        "http://stream.whus.org:8000/whusfm",
        "https://server.webnetradio.net/proxy/wsjf",
        "http://powerrbhiphop.listenpowerapp.com/powerrbhiphop/mpeg/icecast.audio",
        "http://146.71.118.220:35025/autodj",
        "http://stream.rockantenne.de/70er-rock/stream/mp3",
    )

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay = findViewById(R.id.button_play)
        buttonStop = findViewById(R.id.button_stop)
        container = findViewById(R.id.container)

        buttonPlay.setOnClickListener{
            toggleRadioPlayback()
        }

        buttonStop.setOnClickListener{
            stopRadio()
        }

        createStationButtons()
    }

    private fun stopRadio() {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.reset() // Reset the MediaPlayer to its uninitialized state
            buttonPlay.text = "Play"
            radioOn = false
        } catch (e: IllegalStateException) {
            Log.e(TAG, "Error stopping radio playback: ${e.message}")
        }
    }

    private fun setUpRadio(stationIndex: Int) {
        try {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(radioStations[stationIndex])
                setOnPreparedListener {
                    Log.d(TAG, "Radio prepared, playback ready")
                    startRadioPlayback()
                }
                setOnErrorListener { mp, what, extra ->
                    Log.e(TAG, "Error occurred while preparing media player: what=$what, extra=$extra")
                    true
                }
                prepareAsync()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error setting up MediaPlayer: ${e.message}")
        }
    }

    private fun startRadioPlayback() {
        try {
            mediaPlayer.start()
            buttonPlay.text = "Pause"
            radioOn = true
        } catch (e: IllegalStateException) {
            Log.e(TAG, "Error starting radio playback: ${e.message}")
        }
    }

    private fun toggleRadioPlayback() {
        try {
            if (radioOn) {
                mediaPlayer.pause()
                buttonPlay.text = "Play"
            } else {
                mediaPlayer.start()
                buttonPlay.text = "Pause"
            }
            radioOn = !radioOn
        } catch (e: Exception) {
            Log.e(TAG, "Error toggling radio playback: ${e.message}")
        }
    }


    private fun createStationButtons() {
        try {

            container.gravity = Gravity.CENTER_HORIZONTAL

            for ((index, _) in radioStations.withIndex()) {
                val button = Button(this).apply {
                    text = "Station ${index + 1}"
                    textSize = 14f
                    setTextColor(ContextCompat.getColor(context, R.color.white))
                    setBackgroundColor(ContextCompat.getColor(context, R.color.blue))
                    val layoutParams = LinearLayout.LayoutParams(
                        resources.getDimensionPixelSize(R.dimen.button_width),
                        resources.getDimensionPixelSize(R.dimen.button_height)
                    )
                    setLayoutParams(layoutParams)
                    val drawableResId = getDrawableResourceId(index)
                    val drawable = ContextCompat.getDrawable(context, drawableResId)
                    val desiredWidth = resources.getDimensionPixelSize(R.dimen.button_image_width)
                    val desiredHeight = resources.getDimensionPixelSize(R.dimen.button_image_height)
                    val scaledDrawable = BitmapDrawable(resources, Bitmap.createScaledBitmap(
                        (drawable as BitmapDrawable).bitmap, desiredWidth, desiredHeight, true))
                    setCompoundDrawablesWithIntrinsicBounds(null, scaledDrawable, null, null)
                    compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.button_image_padding)
                    setOnClickListener {
                        switchRadioStation(index)
                    }
                }
                container.addView(button)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error creating station buttons: ${e.message}")
        }
    }

    private fun getDrawableResourceId(index: Int): Int {
        // Define an array of image resource IDs corresponding to each button
        val drawableResourceIds = arrayOf(
            R.drawable.connecticut_huskies_logo_svg,
            R.drawable.jazz,
            R.drawable.hiphop,
            R.drawable.latin,
            R.drawable.rock_radio
        )
        return drawableResourceIds[index % drawableResourceIds.size]
    }


    private fun switchRadioStation(index: Int) {
        try {
            if (::mediaPlayer.isInitialized) {
                stopRadio() // Stop the current station
                setUpRadio(index) // Set up the new station
            } else {
                setUpRadio(index)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error switching radio station: ${e.message}")
        }
    }
}