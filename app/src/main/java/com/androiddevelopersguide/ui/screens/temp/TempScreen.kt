package com.androiddevelopersguide.ui.screens.temp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun TempScreen(
    navController: NavController,
    viewModel: TempViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Temp Screen",
            )
        },
        mobileUI = {
            Temp()
        }
    )
}

@Composable
fun Temp() {
    Section(
        title = "",
        description = """
            
        """.trimIndent(),
        demo = {

        },
        code = """
            
        """.trimIndent()
    )
}

@Preview
@Composable
fun TempScreenPreview() {
    TempScreen(navController = rememberNavController())
}