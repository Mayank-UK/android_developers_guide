package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.viewmodellifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ViewModelLifecycleScreen(
    navController: NavController,
    viewModel: ViewModelLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Viewmodel lifecycle",
            )
        },
    ) {

    }
}