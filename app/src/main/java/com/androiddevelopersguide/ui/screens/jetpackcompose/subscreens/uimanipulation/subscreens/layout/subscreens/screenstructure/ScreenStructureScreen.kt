package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout.subscreens.screenstructure

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ScreenStructureScreen(
    navController: NavController,
    viewModel: ScreenStructureViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Screen structure",
        mobileUI = {
            SimpleStructure()
        }
    )
}

@Composable
fun SimpleStructure() {
    Section(
        title = "Simple structure",
        description = """
            The screen should follow a consistent simple structure. Divide the UI into different functions and then compose in the top function.
            Avoid nested UI functions to optimize for performance. 
        """.trimIndent(),
        code = """
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
                ) {
                    Temp()
                }
            }

            @Composable
            fun Temp() {
                Section(
                    title = "",
                    description = ""${'"'}
                        
                    ""${'"'}.trimIndent(),
                    demo = {

                    },
                    code = ""${'"'}
                        
                    ""${'"'}.trimIndent()
                )
            }

            @Preview
            @Composable
            fun TempScreenPreview() {
                TempScreen(navController = rememberNavController())
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ScreenStructureScreenPreview() {
    ScreenStructureScreen(navController = rememberNavController())
}