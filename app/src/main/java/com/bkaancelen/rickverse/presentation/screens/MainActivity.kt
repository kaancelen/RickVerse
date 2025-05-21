package com.bkaancelen.rickverse.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bkaancelen.rickverse.presentation.screens.main.BottomBar
import com.yourname.multiverseexplorer.presentation.screens.main.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    val navController = rememberNavController()
    val windowInsets = WindowInsets.systemBars

    MaterialTheme {
        Scaffold(
            bottomBar = { BottomBar(navController) },
            contentWindowInsets = windowInsets
        ) { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
                NavigationGraph(navController)
            }
        }
    }
}
