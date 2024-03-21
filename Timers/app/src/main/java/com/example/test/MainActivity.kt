package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.test.timers.FrontScreenViewModel
import com.example.test.timers.LeftScreenViewModel
import com.example.test.timers.RightScreenViewModel

class MainActivity : ComponentActivity() {

    private val leftViewModel: LeftScreenViewModel by viewModels<LeftScreenViewModel>()
    private val rightViewModel: RightScreenViewModel by viewModels<RightScreenViewModel>()
    private val frontViewModel: FrontScreenViewModel by viewModels<FrontScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation(leftViewModel, rightViewModel, frontViewModel)
        }
    }
}