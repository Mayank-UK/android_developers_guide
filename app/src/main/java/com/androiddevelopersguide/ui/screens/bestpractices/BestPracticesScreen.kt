package com.androiddevelopersguide.ui.screens.bestpractices

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun BestPracticesScreen(
    navController: NavController,
    viewModel: BestPracticesViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Best practices",
            )
        },
    ) {

    }
}