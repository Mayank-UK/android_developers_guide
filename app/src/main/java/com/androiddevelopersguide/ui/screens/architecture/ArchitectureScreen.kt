package com.androiddevelopersguide.ui.screens.architecture

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun ArchitectureScreen(
    navController: NavController,
    viewModel: ArchitectureViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Architecture",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun ArchitectureScreenPreview() {
    ArchitectureScreen(navController = rememberNavController())
}