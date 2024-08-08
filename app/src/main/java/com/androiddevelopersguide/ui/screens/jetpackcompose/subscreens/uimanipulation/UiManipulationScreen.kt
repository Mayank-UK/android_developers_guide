package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun UiManipulationScreen(
    navController: NavController,
    viewModel: UiManipulationViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "UI manipulation",
            )
        },
    ) {
        Links(
            links = viewModel.links,
            navController = navController
        )
    }
}

@Preview
@Composable
fun UiManipulationScreenPreview() {
    UiManipulationScreen(navController = rememberNavController())
}