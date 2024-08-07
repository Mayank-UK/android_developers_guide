package com.androiddevelopersguide.ui.screens.dependencymanagement

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun DependencyManagementScreen(
    navController: NavController,
    viewModel: DependencyManagementViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Dependency management",
            )
        },
    ) {

    }
}