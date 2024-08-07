package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.fragmentlifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun FragmentLifecycleScreen(
    navController: NavController,
    viewModel: FragmentLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Fragment lifecycle",
            )
        },
    ) {

    }
}