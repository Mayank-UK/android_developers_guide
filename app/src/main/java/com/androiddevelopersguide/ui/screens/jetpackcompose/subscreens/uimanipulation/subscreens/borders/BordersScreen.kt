package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.borders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun BordersScreen(
    navController: NavController,
    viewModel: BordersViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Borders",
        mobileUI = {
            Border()
            Outline()
        }
    )
}

@Composable
fun Border() {
    Section(
        title = "Border",
        description = """
            Show borders for an element.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
                    .background(Color.LightGray)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        },
        code = """
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .width(64.dp)
                    .background(Color.LightGray)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        """.trimIndent()
    )
}

@Composable
fun Outline() {
    Section(
        title = "Outline",
        description = """
            There is no system of outline in jetpack compose.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun BordersScreenPreview() {
    BordersScreen(navController = rememberNavController())
}