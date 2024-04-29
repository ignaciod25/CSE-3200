package com.example.lab6video

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowMediaPlayer

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mainActivity = MainActivity()
    }

    @Test
    fun testSetVideoUri() {
        val videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        mainActivity.setVideoUri(videoUrl)

        val mediaPlayer = ShadowMediaPlayer.getLatestMediaPlayer()
        assertNotNull("MediaPlayer should not be null", mediaPlayer)

        assertEquals("MediaPlayer data source should be set to video URL", videoUrl, mediaPlayer.dataSource)
    }

    @Test
    fun testSetRadioUri() {
        val radioUrl = "http://stream.whus.org:8000/whusfm"
        mainActivity.setRadioUri(radioUrl)

        val mediaPlayer = ShadowMediaPlayer.getLatestMediaPlayer()
        assertNotNull("MediaPlayer should not be null", mediaPlayer)

        assertEquals("MediaPlayer data source should be set to radio URL", radioUrl, mediaPlayer.dataSource)
    }
}

