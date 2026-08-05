package com.androiddevelopersguide.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.NightsStay
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Rightmost app-bar action. Shows a moon icon while light, a sun icon while dark. Clicking cycles
 * the theme via [AppChrome.ToggleTheme].
 */
@Composable
fun ThemeToggleButton(
    darkTheme: Boolean,
    onToggle: () -> Unit,
) {
    val (icon, description) = iconFor(darkTheme)
    IconButton(onClick = onToggle) {
        Icon(
            imageVector = icon,
            contentDescription = description
        )
    }
}

private fun iconFor(darkTheme: Boolean): Pair<ImageVector, String> = if (darkTheme) {
    Icons.Rounded.LightMode to "Switch to light theme"
} else {
    Icons.Rounded.NightsStay to "Switch to dark theme"
}
