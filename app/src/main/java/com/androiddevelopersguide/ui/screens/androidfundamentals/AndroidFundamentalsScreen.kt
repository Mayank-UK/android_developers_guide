package com.androiddevelopersguide.ui.screens.androidfundamentals

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun AndroidFundamentalsScreen(
    navController: NavController,
    viewModel: AndroidFundamentalsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Android fundamentals",
            )
        },
    ) {

    }
}