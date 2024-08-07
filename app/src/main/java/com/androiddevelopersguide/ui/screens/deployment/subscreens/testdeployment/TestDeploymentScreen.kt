package com.androiddevelopersguide.ui.screens.deployment.subscreens.testdeployment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun TestDeploymentScreen(
    navController: NavController,
    viewModel: TestDeploymentViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Test deployment",
            )
        },
    ) {

    }
}