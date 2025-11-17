package com.example.ncc_companion.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// -------------------------
//   CUSTOM BLUE SHADES
// -------------------------

// Light Blue Background
private val LightBlueBackground = Color(0xFFE3F2FD) // Very soft light blue

// Dark Blue Background
private val DarkBlueBackground = Color(0xFF0F1A2A) // Deep navy blue

// Surface colors
private val LightSurfaceBlue = Color(0xFFFFFFFF)
private val DarkSurfaceBlue = Color(0xFF1A2B3C)

// -------------------------
//     DARK THEME COLORS
// -------------------------
private val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = NccNavyBlue,
    onPrimary = Color.White,
    primaryContainer = NccNavyLight,
    onPrimaryContainer = Color.White,

    secondary = NccRed,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF8B1A1A),
    onSecondaryContainer = Color(0xFFFFE5E5),

    tertiary = NccDarkBlue,
    onTertiary = Color.White,

    background = DarkBlueBackground,       // 🔵 updated
    onBackground = Color(0xFFE8EAF6),

    surface = DarkSurfaceBlue,             // 🔵 updated
    onSurface = Color(0xFFE8EAF6),

    surfaceVariant = Color(0xFF1E2A4A),
    onSurfaceVariant = Color(0xFFB0BEC5),

    error = NccRed,
    onError = Color.White
)

// -------------------------
//     LIGHT THEME COLORS
// -------------------------
private val LightColorScheme: ColorScheme = lightColorScheme(
    primary = NccNavyBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8EAF6),
    onPrimaryContainer = NccNavyBlue,

    secondary = NccRed,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFEBEE),
    onSecondaryContainer = NccRed,

    tertiary = NccDarkBlue,
    onTertiary = Color.White,

    background = LightBlueBackground,       // 🔵 updated
    onBackground = NccNavyBlue,

    surface = LightSurfaceBlue,             // 🔵 updated
    onSurface = NccNavyBlue,

    surfaceVariant = Color(0xFFBBDEFB),
    onSurfaceVariant = NccDarkBlue,

    error = NccRed,
    onError = Color.White
)

// -------------------------
//       THEME WRAPPER
// -------------------------
@Composable
fun NccCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
