package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.jetpackcomposelifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun JetpackComposeLifecycleReferScreen(
    navController: NavController,
    viewModel: JetpackComposeLifecycleReferViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Jetpack compose lifecycle",
            )
        },
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun JetpackComposeLifecycleReferScreenPreview() {
    JetpackComposeLifecycleReferScreen(navController = rememberNavController())
}