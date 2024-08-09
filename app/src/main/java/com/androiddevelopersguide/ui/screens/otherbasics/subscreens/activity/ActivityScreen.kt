package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Activity",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun ActivityScreenPreview() {
    ActivityScreen(navController = rememberNavController())
}