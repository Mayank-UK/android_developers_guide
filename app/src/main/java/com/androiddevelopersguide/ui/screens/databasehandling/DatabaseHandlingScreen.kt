package com.androiddevelopersguide.ui.screens.databasehandling

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun DatabaseHandlingScreen(
    navController: NavController,
    viewModel: DatabaseHandlingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Database handling",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun DatabaseHandlingScreenPreview() {
    DatabaseHandlingScreen(navController = rememberNavController())
}