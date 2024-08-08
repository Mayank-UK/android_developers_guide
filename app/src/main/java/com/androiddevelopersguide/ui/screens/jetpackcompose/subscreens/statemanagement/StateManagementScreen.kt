package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.statemanagement

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun StateManagementScreen(
    navController: NavController,
    viewModel: StateMangementViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "State management",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun StateManagementScreenPreview() {
    StateManagementScreen(navController = rememberNavController())
}