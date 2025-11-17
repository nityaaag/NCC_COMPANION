package com.example.ncc_companion.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ncc_companion.ui.theme.NccCompanionTheme
import com.example.ncc_companion.ui.theme.NccDarkBlue
import com.example.ncc_companion.ui.theme.NccNavyBlue
import com.example.ncc_companion.ui.theme.NccRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500), label = ""
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500)
        onNavigateToHome()
    }

    NccCompanionTheme {
        Splash(alpha = alphaAnim.value)
    }
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NccNavyBlue,
                        NccDarkBlue,
                        NccNavyBlue
                    )
                )
            )
            .fillMaxSize()
            .alpha(alpha = alpha),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "NCC",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = NccRed
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha = alpha * 0.7f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = "Companion",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.padding(bottom = 100.dp)
            )
        }
    }
}

