package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.screens.temp.TempScreen

@Composable
fun LayoutScreen(
    navController: NavController,
    viewModel: LayoutViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Layout",
            )
        },
        {
            Links(
                links = viewModel.links,
                navController = navController
            )
        }
    )
}

@Preview
@Composable
fun LayoutScreenPreview() {
    LayoutScreen(navController = rememberNavController())
}