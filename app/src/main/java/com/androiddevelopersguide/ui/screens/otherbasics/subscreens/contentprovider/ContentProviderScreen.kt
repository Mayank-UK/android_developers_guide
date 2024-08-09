package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.contentprovider

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun ContentProviderScreen(
    navController: NavController,
    viewModel: ContentProviderViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Content provider",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun ContentProviderScreenPreview() {
    ContentProviderScreen(navController = rememberNavController())
}