package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.servicelifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.screens.lifecycle.LifecycleScreen

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

@Preview
@Composable
fun ServiceLifecycleScreenPreview() {
    ServiceLifecycleScreen(navController = rememberNavController())
}