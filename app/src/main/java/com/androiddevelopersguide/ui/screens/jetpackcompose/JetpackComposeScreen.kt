package com.androiddevelopersguide.ui.screens.jetpackcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun JetpackComposeScreen(
    navController: NavController,
    viewModel: JetpackComposeViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Jetpack compose",
        mobileUI = {
            Links(
                links = viewModel.links,
                navController = navController
            )
        }
    )
}

@Preview
@Composable
fun JetpackComposeScreenPreview() {
    JetpackComposeScreen(navController = rememberNavController())
}