package com.bkaancelen.rickverse.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bkaancelen.rickverse.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    data object Home : BottomNavItem("home", R.string.home, R.drawable.ic_home)
    data object Characters : BottomNavItem("characters", R.string.characters, R.drawable.ic_characters)
    data object Episodes : BottomNavItem("episodes", R.string.episodes, R.drawable.ic_episodes)
    data object Portal : BottomNavItem("portal", R.string.portal, R.drawable.ic_portal)
    data object Profile : BottomNavItem("profile", R.string.profile, R.drawable.ic_profile)
}
