package com.androiddevelopersguide.ui.screens.deployment.subscreens.testdeployment

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

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

@Preview
@Composable
fun TestDeploymentScreenPreview() {
    TestDeploymentScreen(navController = rememberNavController())
}