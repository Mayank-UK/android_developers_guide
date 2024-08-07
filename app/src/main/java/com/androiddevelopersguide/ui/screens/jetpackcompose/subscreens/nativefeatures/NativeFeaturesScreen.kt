package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.nativefeatures

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun NativeFeaturesScreen(
    navController: NavController,
    viewModel: NativeFeaturesViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Native features",
            )
        },
    ) {

    }
}