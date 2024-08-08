package com.androiddevelopersguide.ui.screens.deployment.subscreens.proddeployment

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

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

@Preview
@Composable
fun ProdDeploymentScreenPreview() {
    ProdDeploymentScreen(navController = rememberNavController())
}