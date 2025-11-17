package com.example.ncc_companion.media

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.example.ncc_companion.R

data class NccSongPlayerState(
    val player: ExoPlayer,
    val isPlaying: Boolean,
    val onTogglePlayback: () -> Unit
)

@OptIn(UnstableApi::class)
@Composable
fun rememberNccSongPlayer(): NccSongPlayerState {

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {

            // Load NCC song from /res/raw
            val mediaItem = MediaItem.fromUri(
                RawResourceDataSource.buildRawResourceUri(R.raw.ncc_song)
            )
            setMediaItem(mediaItem)
            prepare()
        }
    }

    var isPlaying by remember { mutableStateOf(false) }

    // Listen for playback changes
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(newStatus: Boolean) {
                isPlaying = newStatus
            }
        }
        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    return NccSongPlayerState(
        player = exoPlayer,
        isPlaying = isPlaying,
        onTogglePlayback = {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.playWhenReady = true
                exoPlayer.play()
            }
        }
    )
}
