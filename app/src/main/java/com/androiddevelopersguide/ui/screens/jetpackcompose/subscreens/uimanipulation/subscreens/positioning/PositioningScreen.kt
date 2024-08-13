package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.positioning

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
fun PositioningScreen(
    navController: NavController,
    viewModel: PositioningViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Positioning",
            )
        },
    ) {
        Positioning()
    }
}

@Composable
fun Positioning() {
    Section(
        title = "Positioning",
        description = """
            Position elements anywhere on the screen, taking them out of the normal flow.
            
            Positioning is done using the Box element and using offset modifier on it's children.
        """.trimIndent(),
        demo = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp)
            ) {
                Box(
                    Modifier
                        .background(Color.Red)
                        .height(64.dp)
                        .width(128.dp)
                )

                Box(
                    Modifier
                        .offset(x = 32.dp, y = 32.dp)
                        .alpha(0.5f)
                        .background(Color.Black)
                        .height(64.dp)
                        .width(128.dp)
                )
            }
        },
        code = """
            Box(
               Modifier
                   .fillMaxWidth()
                   .height(128.dp)
           ) {
               Box(
                   Modifier
                       .background(Color.Red)
                       .height(64.dp)
                       .width(128.dp)
               )

               Box(
                   Modifier
                       .offset(x = 32.dp, y = 32.dp)
                       .alpha(0.5f)
                       .background(Color.Black)
                       .height(64.dp)
                       .width(128.dp)
               )
           }
        """.trimIndent()
    )
}

@Preview
@Composable
fun PositioningScreenPreview() {
    PositioningScreen(navController = rememberNavController())
}