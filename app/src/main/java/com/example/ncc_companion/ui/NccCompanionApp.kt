package com.example.ncc_companion.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ncc_companion.data.NccViewModel
import com.example.ncc_companion.media.rememberNccSongPlayer
import com.example.ncc_companion.ui.nav.BottomNavDestinations
import com.example.ncc_companion.ui.nav.NccDestination
import com.example.ncc_companion.ui.nav.NccNavGraph
import com.example.ncc_companion.ui.theme.NccCompanionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NccCompanionApp(
    viewModel: NccViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine if the current screen should have the main scaffold with top/bottom bars
    val isMainAppScreen = BottomNavDestinations.any { currentRoute?.startsWith(it.route) == true }

    val songPlayerState = rememberNccSongPlayer()

    NccCompanionTheme {
        Scaffold(
            topBar = {
                if (isMainAppScreen) {
                    val currentTopBarDestination = BottomNavDestinations.firstOrNull { destination ->
                        currentRoute?.startsWith(destination.route) == true
                    } ?: NccDestination.Home

                    CenterAlignedTopAppBar(
                        title = { Text(text = stringResource(id = currentTopBarDestination.labelRes)) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            },
            bottomBar = {
                if (isMainAppScreen) {
                    NavigationBar {
                        BottomNavDestinations.forEach { destination ->
                            val selected = currentRoute?.startsWith(destination.route) == true
                            NavigationBarItem(
                                selected = selected,
                                onClick = {
                                    if (!selected) {
                                        navController.navigate(destination.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = destination.icon,
                                        contentDescription = stringResource(id = destination.labelRes)
                                    )
                                },
                                label = { Text(text = stringResource(id = destination.labelRes)) }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NccNavGraph(
                navController = navController,
                viewModel = viewModel,
                playerState = songPlayerState,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
