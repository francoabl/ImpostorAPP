package com.example.impostor.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Primary80,
    onPrimary = Color.White,
    primaryContainer = PrimaryContainer80,
    onPrimaryContainer = Color(0xFF1E1B4B),
    secondary = Secondary80,
    onSecondary = Color.White,
    tertiary = CharacterBlue,
    onTertiary = Color.White,
    tertiaryContainer = CharacterBlueLight,
    onTertiaryContainer = Color(0xFF1E3A8A),
    error = ImpostorRed,
    onError = Color.White,
    errorContainer = ImpostorRedLight,
    onErrorContainer = Color(0xFF7F1D1D),
    background = Color(0xFF0F172A),
    onBackground = Color(0xFFF1F5F9),
    surface = Color(0xFF1E293B),
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = Color(0xFF334155),
    onSurfaceVariant = Color(0xFFCBD5E1)
)

private val LightColorScheme = lightColorScheme(
    primary = Primary40,
    onPrimary = Color.White,
    primaryContainer = PrimaryContainer40,
    onPrimaryContainer = Color(0xFF1E1B4B),
    secondary = Secondary40,
    onSecondary = Color.White,
    tertiary = CharacterBlue,
    onTertiary = Color.White,
    tertiaryContainer = CharacterBlueLight,
    onTertiaryContainer = Color(0xFF1E3A8A),
    error = ImpostorRed,
    onError = Color.White,
    errorContainer = ImpostorRedLight,
    onErrorContainer = Color(0xFF7F1D1D),
    background = Color(0xFFFAFBFC),
    onBackground = Color(0xFF0F172A),
    surface = Color.White,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = Color(0xFFF8FAFC),
    onSurfaceVariant = Color(0xFF64748B)
)

@Composable
fun ImpostorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled to maintain consistent game theming
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
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}