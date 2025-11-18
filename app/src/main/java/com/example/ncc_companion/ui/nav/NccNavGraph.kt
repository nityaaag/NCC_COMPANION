package com.example.ncc_companion.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ncc_companion.data.NccViewModel
import com.example.ncc_companion.media.NccSongPlayerState
import com.example.ncc_companion.model.UploadedPdf
import com.example.ncc_companion.ui.screens.auth.AuthScreen
import com.example.ncc_companion.ui.screens.camps.CampsScreen
import com.example.ncc_companion.ui.screens.certificates.CertificatesScreen
import com.example.ncc_companion.ui.screens.commands.CommandsScreen
import com.example.ncc_companion.ui.screens.home.HomeScreen
import com.example.ncc_companion.ui.screens.ranks.RanksScreen
import com.example.ncc_companion.ui.screens.resources.ResourcesScreen
import com.example.ncc_companion.ui.screens.song.SongScreen
import com.example.ncc_companion.ui.screens.splash.SplashScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NccNavGraph(
    navController: NavHostController,
    viewModel: NccViewModel,
    playerState: NccSongPlayerState,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = NccDestination.Splash.route,
        modifier = modifier
    ) {

        /* ----------------------
           SPLASH
         ---------------------- */
        composable(NccDestination.Splash.route) {
            SplashScreen(
                onTimeout = {
                    val destination = if (FirebaseAuth.getInstance().currentUser != null) {
                        NccDestination.Home.route
                    } else {
                        NccDestination.Auth.route
                    }
                    navController.navigate(destination) {
                        popUpTo(NccDestination.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToHome = {
                    navController.navigate(NccDestination.Home.route) {
                        popUpTo(NccDestination.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        /* ----------------------
           AUTH
         ---------------------- */
        composable(NccDestination.Auth.route) {
            AuthScreen(
                onSignInSuccess = {
                    navController.navigate(NccDestination.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
                    }
                },
                authViewModel = viewModel()
            )
        }

        /* ----------------------
           HOME
         ---------------------- */
        composable(NccDestination.Home.route) {
            val ecoWeeks by viewModel.ecoActivityWeeks.collectAsState()

            HomeScreen(
                featuredEcoWeeks = ecoWeeks.take(2),
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        /* ----------------------
           RANKS
         ---------------------- */
        composable(NccDestination.Ranks.route) {
            val ranks by viewModel.ranks.collectAsState()
            RanksScreen(ranks = ranks)
        }

        /* ----------------------
           COMMANDS
         ---------------------- */
        composable(NccDestination.Commands.route) {
            val commands by viewModel.commands.collectAsState()
            CommandsScreen(commands = commands)
        }

        /* ----------------------
           SONG SCREEN
         ---------------------- */
        composable(NccDestination.Song.route) {
            SongScreen(
                lyrics = viewModel.nccSongLyrics,
                playerState = playerState
            )
        }

        /* ----------------------
           ATTENDANCE / CERTIFICATES
         ---------------------- */
        composable(NccDestination.Certificates.route) {
            CertificatesScreen()
        }

        /* ----------------------
           ECO / CAMPS
         ---------------------- */
        composable(NccDestination.Camps.route) {
            val ecoWeeks by viewModel.ecoActivityWeeks.collectAsState()

            CampsScreen(
                weeks = ecoWeeks,
                onTaskToggle = { taskId -> viewModel.toggleTaskCompletion(taskId) }
            )
        }

        /* ----------------------
           RESOURCES
         ---------------------- */
        composable(NccDestination.Resources.route) {
            val pdfs by viewModel.pdfResources.collectAsState()
            val links by viewModel.resourceLinks.collectAsState()
            val uploads by viewModel.uploadedPdfs.collectAsState()

            ResourcesScreen(
                pdfResources = pdfs,
                resourceLinks = links,
                uploadedPdfs = uploads,
                onPdfUploaded = { name, uri ->
                    viewModel.addUploadedPdf(UploadedPdf(name, uri))
                }
            )
        }
    }
}
