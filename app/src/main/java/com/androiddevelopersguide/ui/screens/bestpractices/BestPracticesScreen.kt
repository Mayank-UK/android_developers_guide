package com.androiddevelopersguide.ui.screens.bestpractices

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun BestPracticesScreen(
    navController: NavController,
    viewModel: BestPracticesViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Best practices",
            )
        },
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun BestPracticesScreenPreview() {
    BestPracticesScreen(navController = rememberNavController())
}