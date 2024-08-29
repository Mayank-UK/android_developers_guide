package com.androiddevelopersguide.ui.screens.androidfundamentals

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun AndroidFundamentalsScreen(
    navController: NavController,
    viewModel: AndroidFundamentalsViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Android fundamentals",
        mobileUI = {
            GeneralInformation()
            InstallationAndSetup()
            Development()
            GeneralInformation()
        }
    )
}

@Composable
fun GeneralInformation() {
    Section(
        title = "General information",
        description = """
            Android is a linux based, open-source operating system developed by google.
            
            App development is done using java and kotlin, kotlin being preferred by google.
            
            It is the most popular operating system for mobile devices.
            
            Features of android are:
            -Open Source
            -Customization
            -Multi-Tasking
            -Security
            -Google Play Store
            -etc
        """.trimIndent()
    )
}

@Composable
fun InstallationAndSetup() {
    Section(
        title = "Installation & setup",
        description = """
            Install the latest version of android studio and follow the steps to download all the requirements incluing android SDK.
        """.trimIndent()
    )
}

@Composable
fun Development() {
    Section(
        title = "Development",
        description = """
            Start the development by creating a new project or opening an existing android project.
        
            Android Studio is the most preferred IDE for android development. It has preview functionality which is very fast for UI development.
        """.trimIndent()
    )
}

@Preview
@Composable
fun AndroidFundamentalsScreenPreview() {
    AndroidFundamentalsScreen(navController = rememberNavController())
}