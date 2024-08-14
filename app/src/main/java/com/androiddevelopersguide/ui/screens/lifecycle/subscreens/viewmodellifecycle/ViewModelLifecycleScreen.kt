package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.viewmodellifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun ViewModelLifecycleScreenPreview() {
    ViewModelLifecycleScreen(navController = rememberNavController())
}