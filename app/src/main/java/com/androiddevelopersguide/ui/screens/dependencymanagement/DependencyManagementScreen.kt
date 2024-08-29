package com.androiddevelopersguide.ui.screens.dependencymanagement

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DependencyManagementScreen(
    navController: NavController,
    viewModel: DependencyManagementViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Build tools & dependencies",
        mobileUI = {
            Gradle()
            Dependencies()
            AndroidX()
            Repositories()
        }
    )
}

@Composable
fun Gradle() {
    Section(
        title = "Gradle",
        description = """
            Android uses Gradle as its build system, which provides a way to manage dependencies. Gradle is a build automation tool that allows you to declare dependencies, compile, and package your app.
            
            In an Android project, there are two build.gradle files:
            
            -build.gradle (project-level): This file defines the project-wide dependencies and configurations.
            
            -build.gradle (module-level): This file defines the module-specific dependencies and configurations.
        """.trimIndent()
    )
}

@Composable
fun Dependencies() {
    Section(
        title = "Dependencies",
        description = """
            Dependencies are declared in the dependencies block of the build.gradle file. 
            
            There are two types of dependencies:

            Compile-time dependencies: These are dependencies that are required to compile your app's code. Examples include libraries like OkHttp, Retrofit, and Gson.
            
            Runtime dependencies: These are dependencies that are required at runtime, but not at compile-time. Examples include libraries like Firebase and Google Play Services.
        """.trimIndent()
    )
}

@Composable
fun AndroidX() {
    Section(
        title = "AndroidX",
        description = """
            AndroidX is a set of libraries that provide a more consistent and efficient way of managing dependencies. AndroidX replaces the Android Support Library and provides a more modular approach to dependency management.
        """.trimIndent()
    )
}

@Composable
fun Repositories() {
    Section(
        title = "Repositories",
        description = """
            Maven and JCenter are two popular repositories that provide a vast collection of libraries and dependencies. You can declare dependencies from these repositories in your build.gradle file.
        """.trimIndent()
    )
}

@Preview
@Composable
fun DependencyManagementScreenPreview() {
    DependencyManagementScreen(navController = rememberNavController())
}