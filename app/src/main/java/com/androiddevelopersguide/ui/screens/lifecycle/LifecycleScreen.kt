package com.androiddevelopersguide.ui.screens.lifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun LifecycleScreen(
    navController: NavController,
    viewModel: LifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Lifecycle",
            )
        },
    ) {
        Links(
            links = viewModel.links,
            navController = navController
        )
    }
}