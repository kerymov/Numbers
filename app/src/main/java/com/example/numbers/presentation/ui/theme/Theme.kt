package com.example.numbers.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Green,
    secondary = GreenDark,
    background = GreenLight,
    surface = Light,
    onPrimary = Light,
    onSecondary = Light,
    onBackground = GreenDark,
    onSurface = GreenDark
)

@Composable
fun NumbersTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}