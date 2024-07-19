package com.lhfp.studifydemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = OxfordBlue_A100,
    onPrimary = OxfordBlue_A100,
    secondary = OxfordBlue_300,
    onSecondary = OxfordBlue_400,
    tertiary = PennRed_500,
    background = OxfordBlue_900,
    onBackground = PaleWhite,
    primaryContainer = OxfordBlue_400,
    onPrimaryContainer = PaleWhite,
    surface = OxfordBlue_500,
    onSurface = OxfordBlue_600
)

private val LightColorScheme = lightColorScheme(
    primary = OxfordBlue_A100,
    onPrimary = PaleWhite,
    secondary = OxfordBlue_300,
    onSecondary = OxfordBlue_400,
    tertiary = PennRed_500,
    background = PaleWhite,
    onBackground = PaleBlack,
    primaryContainer = PaleWhite,
    onPrimaryContainer = OxfordBlue_900,
    surface = OxfordBlue_500,
    onSurface = OxfordBlue_A100

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun StudifyDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}