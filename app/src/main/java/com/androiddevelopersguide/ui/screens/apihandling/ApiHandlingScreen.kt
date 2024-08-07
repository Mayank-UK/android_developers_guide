package com.androiddevelopersguide.ui.screens.apihandling

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ApiHandlingScreen(
    navController: NavController,
    viewModel: ApiHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Api handling",
            )
        },
    ) {

    }
}