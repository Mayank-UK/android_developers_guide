package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold

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