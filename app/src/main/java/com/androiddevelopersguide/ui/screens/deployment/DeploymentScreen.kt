package com.androiddevelopersguide.ui.screens.deployment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun DeploymentScreen(
    navController: NavController,
    viewModel: DeploymentViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Deployment",
            )
        },
    ) {
        Links(
            links = viewModel.links,
            navController = navController
        )
    }
}