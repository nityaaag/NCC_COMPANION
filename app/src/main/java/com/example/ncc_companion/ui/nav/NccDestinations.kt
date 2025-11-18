package com.example.ncc_companion.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ncc_companion.R

/**
 * Central navigation destinations used across the app.
 * Each destination contains:
 * - route: Navigation route
 * - labelRes: String resource for bottom nav label
 * - icon: Vector icon for bottom nav
 */
sealed class NccDestination(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector
) {

    // ----------------------- AUTH -----------------------
    data object Auth :
        NccDestination("auth", R.string.nav_home, Icons.Default.Home) // Icon and label are placeholders

    // ----------------------- MAIN SCREENS -----------------------
    data object Home :
        NccDestination("home", R.string.nav_home, Icons.Default.Home)

    data object Ranks :
        NccDestination("ranks", R.string.nav_ranks, Icons.Default.Flag)

    data object Commands :
        NccDestination("commands", R.string.nav_commands, Icons.Default.List)

    data object Song :
        NccDestination("song", R.string.nav_song, Icons.Default.LibraryMusic)

    data object Certificates :
        NccDestination("certificates", R.string.nav_certificates, Icons.Default.School)

    data object Camps :
        NccDestination("camps", R.string.nav_camps, Icons.Default.Category)

    data object Resources :
        NccDestination("resources", R.string.nav_resources, Icons.Default.Assignment)

    // ----------------------- NON-BOTTOM NAV -----------------------
    data object Splash :
        NccDestination("splash", R.string.nav_home, Icons.Default.Home)
}

/**
 * Destinations that appear in the Bottom Navigation Bar.
 * Excludes Splash screen because splash is only shown once.
 */
val BottomNavDestinations = listOf(
    NccDestination.Home,
    NccDestination.Ranks,
    NccDestination.Commands,
    NccDestination.Song,
    NccDestination.Certificates,
    NccDestination.Camps,
    NccDestination.Resources
)
