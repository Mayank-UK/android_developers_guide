package com.androiddevelopersguide.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Api
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.ViewQuilt
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.ui.graphics.vector.ImageVector
import com.androiddevelopersguide.domain.models.LinkIcon

/**
 * UI-layer resolver that maps the domain [LinkIcon] key to a concrete Compose [ImageVector].
 * Keeping this in the UI layer keeps `domain/models` free of Android framework imports.
 */
fun LinkIcon.toImageVector(): ImageVector = when (this) {
    LinkIcon.ANDROID          -> Icons.Filled.Dashboard
    LinkIcon.KOTLIN           -> Icons.Filled.Code
    LinkIcon.COMPOSE          -> Icons.Filled.AutoAwesome
    LinkIcon.BASICS           -> Icons.AutoMirrored.Filled.MenuBook
    LinkIcon.LIFECYCLE        -> Icons.Filled.Loop
    LinkIcon.WIDGETS          -> Icons.Filled.Widgets
    LinkIcon.DEPENDENCY       -> Icons.Filled.Extension
    LinkIcon.API              -> Icons.Filled.Api
    LinkIcon.DATABASE         -> Icons.Filled.Storage
    LinkIcon.ARCHITECTURE     -> Icons.Filled.Architecture
    LinkIcon.DEPLOYMENT       -> Icons.Filled.Build
    LinkIcon.BEST_PRACTICES   -> Icons.Filled.AutoAwesome
    LinkIcon.XML              -> Icons.Filled.ViewQuilt
}
