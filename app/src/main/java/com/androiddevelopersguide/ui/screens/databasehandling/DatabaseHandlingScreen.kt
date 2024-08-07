package com.androiddevelopersguide.ui.screens.databasehandling

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun DatabaseHandlingScreen(
    navController: NavController,
    viewModel: DatabaseHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Database handling",
            )
        },
    ) {

    }
}