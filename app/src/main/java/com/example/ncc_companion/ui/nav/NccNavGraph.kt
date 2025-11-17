package com.example.ncc_companion.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ncc_companion.data.NccViewModel
import com.example.ncc_companion.media.NccSongPlayerState
import com.example.ncc_companion.model.UploadedPdf
import com.example.ncc_companion.ui.screens.camps.CampsScreen
import com.example.ncc_companion.ui.screens.certificates.CertificatesScreen
import com.example.ncc_companion.ui.screens.commands.CommandsScreen
import com.example.ncc_companion.ui.screens.home.HomeScreen
import com.example.ncc_companion.ui.screens.ranks.RanksScreen
import com.example.ncc_companion.ui.screens.resources.ResourcesScreen
import com.example.ncc_companion.ui.screens.song.SongScreen

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

        /* -------------------------------------------------------------
         *                        SPLASH SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Splash.route) {
            com.example.ncc_companion.ui.screens.splash.SplashScreen(
                onNavigateToHome = {
                    navController.navigate(NccDestination.Home.route) {
                        popUpTo(NccDestination.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        /* -------------------------------------------------------------
         *                        HOME SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Home.route) {
            val ecoWeeks by viewModel.ecoActivityWeeks.collectAsState()

            HomeScreen(
                featuredEcoWeeks = ecoWeeks.take(2),
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        /* -------------------------------------------------------------
         *                        RANKS SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Ranks.route) {
            val ranks by viewModel.ranks.collectAsState()
            RanksScreen(ranks = ranks)
        }

        /* -------------------------------------------------------------
         *                        COMMANDS SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Commands.route) {
            val commands by viewModel.commands.collectAsState()
            CommandsScreen(commands = commands)
        }

        /* -------------------------------------------------------------
         *                        NCC SONG SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Song.route) {
            SongScreen(
                lyrics = viewModel.nccSongLyrics,
                playerState = playerState
            )
        }

        /* -------------------------------------------------------------
         *               ATTENDANCE / CERTIFICATES SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Certificates.route) {
            val attendance by viewModel.attendance.collectAsState()

            CertificatesScreen(
                attendance = attendance
            )
        }

        /* -------------------------------------------------------------
         *                  CAMPS / ECO ACTIVITY SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Camps.route) {
            val ecoWeeks by viewModel.ecoActivityWeeks.collectAsState()

            CampsScreen(
                weeks = ecoWeeks,
                onTaskToggle = { taskId ->
                    viewModel.toggleTaskCompletion(taskId)
                }
            )
        }

        /* -------------------------------------------------------------
         *                        RESOURCES SCREEN
         * ------------------------------------------------------------- */
        composable(NccDestination.Resources.route) {
            val pdfResources by viewModel.pdfResources.collectAsState()
            val resourceLinks by viewModel.resourceLinks.collectAsState()
            val uploadedPdfs by viewModel.uploadedPdfs.collectAsState()

            ResourcesScreen(
                pdfResources = pdfResources,
                resourceLinks = resourceLinks,
                uploadedPdfs = uploadedPdfs,
                onPdfUploaded = { name, uri ->
                    viewModel.addUploadedPdf(UploadedPdf(name, uri))
                }
            )
        }
    }
}
