package com.androiddevelopersguide.ui.screens.deployment.subscreens.proddeployment

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ProdDeploymentScreen(
    navController: NavController,
    viewModel: ProdDeploymentViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Prod deployment",
            )
        },
    ) {

    }
}