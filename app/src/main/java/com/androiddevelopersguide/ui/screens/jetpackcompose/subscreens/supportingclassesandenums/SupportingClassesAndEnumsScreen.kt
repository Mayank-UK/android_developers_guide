package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.supportingclassesandenums

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun SupportingClassesAndEnumsScreen(
    navController: NavController,
    viewModel: SupportingClassesAndEnumsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Supporting classes & enums",
            )
        },
    ) {

    }
}

@Preview
@Composable
fun SupportingClassesAndEnumsScreenPreview() {
    SupportingClassesAndEnumsScreen(navController = rememberNavController())
}