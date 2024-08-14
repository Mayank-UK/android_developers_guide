package com.androiddevelopersguide.ui.screens.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun LifecycleScreen(
    navController: NavController,
    viewModel: LifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Lifecycle",
            )
        },
        mobileUI = {
            Links(
                links = viewModel.links,
                navController = navController
            )
        }
    )
}

@Preview
@Composable
fun LifecycleScreenPreview() {
    LifecycleScreen(navController = rememberNavController())
}