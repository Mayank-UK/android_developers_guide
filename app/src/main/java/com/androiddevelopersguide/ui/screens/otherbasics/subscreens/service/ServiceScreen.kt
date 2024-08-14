package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.service

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ServiceScreen(
    navController: NavController,
    viewModel: ServiceViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Service",
            )
        },
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun ServiceScreenPreview() {
    ServiceScreen(navController = rememberNavController())
}