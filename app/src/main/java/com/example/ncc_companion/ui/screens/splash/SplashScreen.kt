package com.example.ncc_companion.ui.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ncc_companion.R
import com.example.ncc_companion.ui.theme.NccCompanionTheme
import com.example.ncc_companion.ui.theme.NccDarkBlue
import com.example.ncc_companion.ui.theme.NccNavyBlue
import com.example.ncc_companion.ui.theme.NccRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToHome: () -> Unit
) {
    var start by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = tween(1500),
        label = ""
    )

    val scaleAnim by animateFloatAsState(
        targetValue = if (start) 1f else 0.7f,
        animationSpec = tween(1200),
        label = ""
    )

    LaunchedEffect(true) {
        start = true
        delay(2500)
        onNavigateToHome()
    }

    NccCompanionTheme {
        Splash(alphaAnim, scaleAnim)
    }
}

@Composable
fun Splash(alpha: Float, scale: Float) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NccNavyBlue,
                        NccDarkBlue,
                        NccNavyBlue
                    )
                )
            )
            .alpha(alpha),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale)
        ) {

            // NCC LOGO (use your image)
            Image(
                painter = painterResource(id = R.drawable.images),
                contentDescription = "NCC Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.material3.Text(
                    text = "NCC",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = NccRed
                )
                Spacer(modifier = Modifier.width(12.dp))
                androidx.compose.material3.Text(
                    text = "Companion",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
    }
}
