package com.example.test.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.timers.LeftScreenViewModel
import com.example.test.timers.RightScreenViewModel
import kotlinx.coroutines.delay


@Composable
fun LeftScreen(name: String, myViewModel: LeftScreenViewModel) {
    val currentTime = remember { mutableStateOf(0L) }
    val isTimerRunning = remember { mutableStateOf(false) }
    val timerScope = rememberCoroutineScope()

    LaunchedEffect(isTimerRunning.value) {
        if (isTimerRunning.value) {
            while (true) {
                delay(1000)
                currentTime.value++
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(0.75F)
        ) {
            Text("Timer")
            Spacer(modifier = Modifier.padding(25.dp))
            Text("Time elapsed: ${currentTime.value} seconds", fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    isTimerRunning.value = !isTimerRunning.value
                }
            ) {
                Text(if (isTimerRunning.value) "Stop Timer" else "Start Timer")
            }
        }
    }
}