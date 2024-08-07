package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.activitylifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ActivityLifecycleScreen(
    navController: NavController,
    viewModel: ActivityLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Activity lifecycle",
            )
        },
    ) {

    }
}