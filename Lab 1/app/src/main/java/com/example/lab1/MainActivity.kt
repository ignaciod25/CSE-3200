// MainActivity.kt
package com.example.lab1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab1.ui.theme.Lab1Theme
import kotlin.random.Random

const val ON_CREATE = "ON_CREATE"
const val ON_START = "ON_START"
const val ON_STOP = "ON_STOP"
const val ON_PAUSE = "ON_PAUSE"
const val ON_RESUME = "ON_RESUME"
const val ON_DESTROY = "ON_DESTROY"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab1Theme {
                MyApp()

                // Call the main function from Main.kt
            }
        }
        Log.i(ON_CREATE, "ONCREATE called")
    }

    override fun onStart() {
        super.onStart()
        Log.i(ON_START, "onStart called")
    }

    override fun onPause() {
        super.onPause()
        Log.i(ON_PAUSE, "onPause called")
    }

    override fun onResume() {
        super.onResume()
        Log.i(ON_RESUME, "onResume called")
    }

    override fun onStop() {
        super.onStop()
        Log.i(ON_STOP, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(ON_DESTROY, "onDestroy called")
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current
    val density = LocalDensity.current.density
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val randomNumber = generateRandomNumber(1, 100)
                    Log.i("MyApp", "Random Number: $randomNumber")
                    context.startActivity(Intent(context, SecondActivity::class.java).apply {
                        putExtra("randomNumber", randomNumber)
                    })
                }
            ) {
                Text(text = "Click To Generate Random Number")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val randomNumber = intent.getIntExtra("randomNumber", 0)

        setContent {
            Lab1Theme {
                SecondScreen(randomNumber) {
                    // Handle the "Try Again" button click by navigating back to MainActivity
                    finish()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecondScreen(randomNumber: Int, onTryAgain: () -> Unit) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Random Number: $randomNumber")

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onTryAgain) {
                Text(text = "Try Again")
            }
        }
    }
}


fun generateRandomNumber(min: Int, max: Int): Int {
    require(min < max) { "min must be less than max" }
    return Random.nextInt(min, max + 1)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab1Theme {
        MyApp()
    }
}
