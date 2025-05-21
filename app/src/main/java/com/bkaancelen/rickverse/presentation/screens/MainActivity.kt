package com.bkaancelen.rickverse.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiverseApp()
        }
    }
}

@Composable
fun MultiverseApp() {
    MaterialTheme {
        Surface {
            HelloWorldScreen()
        }
    }
}

@Composable
fun HelloWorldScreen() {
    Text(text = "Hello, Multiverse 🌌")
}