package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.jetpackcomposelifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun JetpackComposeLifecycleReferScreen(
    navController: NavController,
    viewModel: JetpackComposeLifecycleReferViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Jetpack compose lifecycle",
            )
        },
    ) {

    }
}