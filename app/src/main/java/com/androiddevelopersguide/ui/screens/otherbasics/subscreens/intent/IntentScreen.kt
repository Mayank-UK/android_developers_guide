package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.intent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun IntentScreen(
    navController: NavController,
    viewModel: IntentViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Intent",
            )
        },
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun IntentScreenPreview() {
    IntentScreen(navController = rememberNavController())
}