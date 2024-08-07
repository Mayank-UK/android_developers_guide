package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.servicelifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ServiceLifecycleScreen(
    navController: NavController,
    viewModel: ServiceLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Service lifecycle",
            )
        },
    ) {

    }
}