package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.nativefeatures

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun NativeFeaturesScreen(
    navController: NavController,
    viewModel: NativeFeaturesViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Native features",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun NativeFeaturesScreenPreview() {
    NativeFeaturesScreen(navController = rememberNavController())
}