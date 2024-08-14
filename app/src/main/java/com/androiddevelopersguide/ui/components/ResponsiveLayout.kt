package com.androiddevelopersguide.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun ResponsiveLayout(
    mobileUI: @Composable () -> Unit,
    tabletUI: @Composable () -> Unit,
    desktopUI: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val density = LocalDensity.current.density
        val maxWidthPx = constraints.maxWidth

        // Convert dp to pixels
        val mobileThresholdPx = (600.dp.value * density).toInt()
        val tabletThresholdPx = (1024.dp.value * density).toInt()

        when {
            maxWidthPx < mobileThresholdPx -> mobileUI()
            maxWidthPx in mobileThresholdPx..tabletThresholdPx -> tabletUI()
            else -> desktopUI()
        }
    }
}

