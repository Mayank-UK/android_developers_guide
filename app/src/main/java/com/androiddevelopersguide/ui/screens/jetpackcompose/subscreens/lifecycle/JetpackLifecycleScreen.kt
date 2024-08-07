package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.lifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun JetpackLifecycleScreen(
    navController: NavController,
    viewModel: JetpackLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Lifecycle",
            )
        },
    ) {

    }
}