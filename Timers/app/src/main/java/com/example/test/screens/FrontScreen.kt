package com.example.test.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test.timers.FrontScreenViewModel
import com.example.test.timers.LeftScreenViewModel


@Composable
fun FrontScreen(name: String, myViewModel: FrontScreenViewModel) {
    val counter = myViewModel.counter

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(0.75F)
    ) {
        Text(" $name")
        Spacer(modifier = Modifier.padding(25.dp))
        Text(" ${counter.value.toString()}")
        TextField(
            value = counter.value.toString(),
            onValueChange = {  },
            label = { Text("No label") }
        )

        Button(onClick = { myViewModel.increment() }) {
            Text( counter.value.toString() )
        }
    }
}