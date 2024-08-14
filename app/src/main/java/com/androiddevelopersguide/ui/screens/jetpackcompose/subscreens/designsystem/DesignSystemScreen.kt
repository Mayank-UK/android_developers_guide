package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DesignSystemScreen(
    navController: NavController,
    viewModel: DesignSystemViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Design system",
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
    DesignSystemScreen(navController = rememberNavController())
}