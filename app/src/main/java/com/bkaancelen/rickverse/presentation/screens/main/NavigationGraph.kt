package com.yourname.multiverseexplorer.presentation.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bkaancelen.rickverse.presentation.navigation.BottomNavItem
import com.bkaancelen.rickverse.presentation.screens.character.CharacterScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(BottomNavItem.Characters.route) {
            CharacterScreen()
        }
        composable(BottomNavItem.Episodes.route) {
            EpisodeScreen()
        }
        composable(BottomNavItem.Portal.route) {
            PortalScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable fun HomeScreen() = Text("🛸 Home Screen")
@Composable fun EpisodeScreen() = Text("📼 Episode Screen")
@Composable fun PortalScreen() = Text("🌀 Portal Screen")
@Composable fun ProfileScreen() = Text("🧑‍🚀 Profile Screen")
