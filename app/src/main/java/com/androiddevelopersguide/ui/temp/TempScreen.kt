package com.androiddevelopersguide.ui.temp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun TempScreen(
    navController: NavController,
    viewModel: TempViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Temp Screen",
            )
        },
    ) {

    }
}