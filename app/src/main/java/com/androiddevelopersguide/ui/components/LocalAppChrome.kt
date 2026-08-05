package com.androiddevelopersguide.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.androiddevelopersguide.domain.models.AppTheme

/**
 * Cross-cutting hooks injected at the activity root so the chrome (TopAppBar, drawer) can react
 * without each screen having to thread callbacks through its signature.
 */
object AppChrome {
    /** Opens the navigation drawer. No-op by default. */
    val OpenDrawer = compositionLocalOf<() -> Unit> { {} }

    /** Resolved dark-theme state used to pick the toggle's icon. Defaults to the platform value. */
    val IsDarkTheme = compositionLocalOf { false }

    /** Cycles the theme mode (Light -> Dark -> System -> Light). No-op by default. */
    val ToggleTheme = compositionLocalOf<() -> Unit> { {} }
}

/**
 * Resolves whether dark theming is currently in effect for [theme], honouring [AppTheme.System]
 * by deferring to the platform setting.
 */
@Composable
fun resolveDarkTheme(theme: AppTheme): Boolean = when (theme) {
    AppTheme.Light -> false
    AppTheme.Dark -> true
    AppTheme.System -> isSystemInDarkTheme()
}
