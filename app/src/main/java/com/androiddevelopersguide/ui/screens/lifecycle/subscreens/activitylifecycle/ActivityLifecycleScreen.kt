package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.activitylifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun ActivityLifecycleScreenPreview() {
    ActivityLifecycleScreen(navController = rememberNavController())
}