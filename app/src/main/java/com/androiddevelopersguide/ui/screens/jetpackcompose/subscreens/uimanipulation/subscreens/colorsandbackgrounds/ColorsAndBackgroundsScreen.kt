package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.colorsandbackgrounds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ColorsAndBackgroundsScreen(
    navController: NavController,
    viewModel: ColorsAndBackgroundsViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Colors & backgrounds",
        mobileUI = {
            TextColor()
            BackgroundColor()
        }
    )
}

@Composable
fun TextColor() {
    Section(
        title = "Text color",
        description = """
            To apply color to text.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    "White color text",
                    color = Color.White
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    "White color text",
                    color = Color.White
                )
            }
        """.trimIndent()
    )
}

@Composable
fun BackgroundColor() {
    Section(
        title = "Background color",
        description = """
            To apply background color to elements.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    "Black color background",
                    color = Color.White
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                Text(
                    "Black color background",
                    color = Color.White
                )
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ColorsAndBackgroundsScreenPreview() {
    ColorsAndBackgroundsScreen(navController = rememberNavController())
}