package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.responsivedesign

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ResponsiveDesignScreen(
    navController: NavController,
    viewModel: ResponsiveDesignViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Responsive design",
            )
        },
    ) {

    }
}