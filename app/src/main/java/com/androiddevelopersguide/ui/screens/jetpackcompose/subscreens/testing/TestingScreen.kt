package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.testing

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun TestingScreen(
    navController: NavController,
    viewModel: TestingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Testing",
            )
        },
    ) {

    }
}