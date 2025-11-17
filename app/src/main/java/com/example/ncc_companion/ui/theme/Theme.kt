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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = NccPrimary,
    onPrimary = Color.White,
    primaryContainer = NccTertiary,
    onPrimaryContainer = Color.White,
    secondary = NccSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFF5F1212),
    onSecondaryContainer = Color(0xFFFFD5D5),
    tertiary = NccTertiary,
    onTertiary = Color.White,
    background = NccBackgroundDark,
    onBackground = Color.White,
    surface = Color(0xFF111827),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF1F2937),
    onSurfaceVariant = Color(0xFFD0D6E0)
)

private val LightColorScheme: ColorScheme = lightColorScheme(
    primary = NccPrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF63A4FF),
    onPrimaryContainer = Color(0xFF001C39),
    secondary = NccSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFCDD2),
    onSecondaryContainer = Color(0xFF410005),
    tertiary = NccTertiary,
    onTertiary = Color.White,
    background = Color(0xFFF7F9FC),
    onBackground = Color(0xFF051533),
    surface = Color.White,
    onSurface = Color(0xFF051533),
    surfaceVariant = NccNeutralVariant,
    onSurfaceVariant = Color(0xFF1F2A44)
)

@Composable
fun NccCompanionTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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
