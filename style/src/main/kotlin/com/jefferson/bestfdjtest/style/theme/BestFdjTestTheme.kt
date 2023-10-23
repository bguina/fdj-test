package com.jefferson.bestfdjtest.style.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun FdjTestTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) {
        DarkColorsPalette
    } else {
        LightColorsPalette
    }

    MaterialTheme(
        colorScheme = colors,
        content = content,
    )
}
