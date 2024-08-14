package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.responsivedesign

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.ResponsiveLayout
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ResponsiveDesignScreen(
    navController: NavController,
    viewModel: ResponsiveDesignViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Responsive design",
            )
        },
        mobileUI = { DetectingDeviceDimensions() },
        tabletUI = { DetectingDeviceDimensions() },
        desktopUI = { DetectingDeviceDimensions() }
    )
}

@Composable
fun DetectingDeviceDimensions() {
    Section(
        title = "Detecting device dimensions",
        description = """
            Rendering UI based on device dimensions.
        """.trimIndent(),
        demo = {
            ResponsiveLayout(
                mobileUI = { Text("Mobile UI") },
                tabletUI = { Text("Tablet UI") },
                desktopUI = { Text("Desktop UI") }
            )
        },
        code = """
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
            
            ResponsiveLayout(
                mobileUI = { Text("Mobile UI") },
                tabletUI = { Text("Tablet UI") },
                desktopUI = { Text("Desktop UI") }
            )
        """.trimIndent()
    )
}

@Preview
@Composable
fun ResponsiveDesignScreenPreview() {
    ResponsiveDesignScreen(navController = rememberNavController())
}