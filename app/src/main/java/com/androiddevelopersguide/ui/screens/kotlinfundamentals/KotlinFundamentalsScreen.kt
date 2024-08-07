package com.androiddevelopersguide.ui.screens.kotlinfundamentals

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun KotlinFundamentalsScreen(
    navController: NavController,
    viewModel: KotlinFundamentalsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Kotlin fundamentals",
            )
        },
    ) {

    }
}