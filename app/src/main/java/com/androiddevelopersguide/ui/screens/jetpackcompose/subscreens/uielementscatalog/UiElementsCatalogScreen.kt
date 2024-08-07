package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uielementscatalog

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun UiElementsCatalogScreen(
    navController: NavController,
    viewModel: UiElementsCatalogViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "UI elements catalog",
            )
        },
    ) {
        Links(
            links = viewModel.links,
            navController = navController
        )
    }
}