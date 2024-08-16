package com.androiddevelopersguide.ui.screens.bestpractices

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun BestPracticesScreen(
    navController: NavController,
    viewModel: BestPracticesViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Best practices",
            )
        },
        mobileUI = {
            GeneralBestPractices()
            UiBestPractices()
        }
    )
}

@Composable
fun GeneralBestPractices() {
    Section(
        title = "General best practices",
        description = """
            -Fundamentals should be clear to write code faster and better.
            
            -Follow proper architecture which will help structure the directories, use strategies like dependency injection, and other development principles.
               
            -Write optimized code for performance.
            
            -Follow consistent naming scheme for the whole project.
            
            -Consider security while handling sensitive data.
            
            -Follow test driven development.
            
            -Use version control system like git.
            
            -Write a very clear and standardised documentation for the project and add comments to explain complicated chunks of code.
            
            -Implement analytics and monitoring for production grade applications.
        """.trimIndent(),
    )
}

@Composable
fun UiBestPractices() {
    Section(
        title = "UI best practices",
        description = """
            -Follow consistent pattern for every screen.
            
            -Implement design system and avoid using custom colors, sizes, typography, etc at different places.
            
            -Divide UI into components for reusability.
            
            -Avoid nesting composable within each other, to optimize for performance.
            
            -Use preview for faster development (available only for compose based UI).
        """.trimIndent(),
    )
}

@Preview
@Composable
fun BestPracticesScreenPreview() {
    BestPracticesScreen(navController = rememberNavController())
}