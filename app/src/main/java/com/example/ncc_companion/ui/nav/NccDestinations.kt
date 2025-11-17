package com.example.ncc_companion.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ncc_companion.R

sealed class NccDestination(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector
) {
    data object Home : NccDestination("home", R.string.nav_home, Icons.Default.Home)
    data object Ranks : NccDestination("ranks", R.string.nav_ranks, Icons.Default.Flag)
    data object Commands : NccDestination("commands", R.string.nav_commands, Icons.Default.List)
    data object Song : NccDestination("song", R.string.nav_song, Icons.Default.LibraryMusic)
    data object Certificates : NccDestination("certificates", R.string.nav_certificates, Icons.Default.School)
    data object Camps : NccDestination("camps", R.string.nav_camps, Icons.Default.Category)
    data object Resources : NccDestination("resources", R.string.nav_resources, Icons.Default.Assignment)
    data object Splash : NccDestination("splash", R.string.nav_home, Icons.Default.Home)
}

val BottomNavDestinations = listOf(
    NccDestination.Home,
    NccDestination.Ranks,
    NccDestination.Commands,
    NccDestination.Song,
    NccDestination.Certificates,
    NccDestination.Camps,
    NccDestination.Resources
)
