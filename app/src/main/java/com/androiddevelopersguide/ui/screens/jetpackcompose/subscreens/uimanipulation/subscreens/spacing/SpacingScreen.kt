package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.spacing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun SpacingScreen(
    navController: NavController,
    viewModel: SpacingViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Spacing",
            )
        },
    ) {
        Padding()
        Margin()
        NormalSpacing()
    }
}

@Composable
fun Padding() {
    Section(
        title = "Padding",
        description = """
            Space between walls of the container/parent and it's children.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        """.trimIndent()
    )
}

@Composable
fun Margin() {
    Section(
        title = "Margin",
        description = """
            Space between the walls of different elements.
            In Jetpack compose there is no margin system, use normal spacing for margin.
        """.trimIndent(),
    )
}

@Composable
fun NormalSpacing() {
    Section(
        title = "Normal spacing",
        description = """
            Space between two elements.
        """.trimIndent(),
        demo = {
            Column {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        },
        code = """
            Column {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun SpacingScreenPreview() {
    SpacingScreen(navController = rememberNavController())
}