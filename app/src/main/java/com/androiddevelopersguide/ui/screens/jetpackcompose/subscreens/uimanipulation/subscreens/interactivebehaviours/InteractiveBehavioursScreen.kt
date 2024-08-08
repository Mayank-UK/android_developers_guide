package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.interactivebehaviours

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun InteractiveBehavioursScreen(
    navController: NavController,
    viewModel: InteractiveBehavioursViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Interactive behaviours",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun InteractiveBehavioursScreenPreview() {
    InteractiveBehavioursScreen(navController = rememberNavController())
}