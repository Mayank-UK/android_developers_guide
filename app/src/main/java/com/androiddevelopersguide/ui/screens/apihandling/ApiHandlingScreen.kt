package com.androiddevelopersguide.ui.screens.apihandling

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ApiHandlingScreen(
    navController: NavController,
    viewModel: ApiHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Api handling",
            )
        },
        mobileUI = {

        }
    )
}

@Preview
@Composable
fun ApiHandlingScreenPreview() {
    ApiHandlingScreen(navController = rememberNavController())
}