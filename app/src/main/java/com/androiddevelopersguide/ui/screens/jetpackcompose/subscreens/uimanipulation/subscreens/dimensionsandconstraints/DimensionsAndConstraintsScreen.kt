package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.dimensionsandconstraints

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DimensionsAndConstraintsScreen(
    navController: NavController,
    viewModel: DimensionsAndConstraintsViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Dimensions & constraints",
        mobileUI = {
            FixedDimensions()
            FixedMinimumDimensions()
            FixedMaximumDimensions()
            FillMaximumDimensions()
            PercentageOfParentDimensions()
            PercentageOfScreenDimensions()
        }
    )
}

@Composable
fun FixedDimensions() {
    Section(
        title = "Fixed dimensions",
        description = """
            Dimensions of the element are fixed to particular numbers like 32.dp, 64.dp, etc.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            ) {

            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            ) {

            }
        """.trimIndent()
    )
}

@Composable
fun FixedMinimumDimensions() {
    Section(
        title = "Fixed minimum dimensions",
        description = """
            Dimensions of the element won't go below the minimum defined units.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .heightIn(min = 32.dp)
                    .widthIn(min = 64.dp)
            ) {

            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .heightIn(min = 32.dp)
                    .widthIn(min = 64.dp)
            ) {

            }
        """.trimIndent()
    )
}

@Composable
fun FixedMaximumDimensions() {
    Section(
        title = "Fixed maximum dimensions",
        description = """
            Dimensions of the element won't go above the maximum defined units.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .heightIn(max = 32.dp)
                    .widthIn(max = 64.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "LongText",
                    color = Color.White
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .heightIn(max = 32.dp)
                    .widthIn(max = 64.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "LongText",
                    color = Color.White
                )
            }
        """.trimIndent()
    )
}

@Composable
fun FillMaximumDimensions() {
    Section(
        title = "Fill maximum dimensions",
        description = """
            Dimensions of the element will try to match it's parent dimensions.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
        """.trimIndent()
    )
}

@Composable
fun PercentageOfParentDimensions() {
    Section(
        title = "Percentage of parent dimensions",
        description = """
            Dimensions of the element are in percentage or fraction of it's parent dimensions.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f)
                )
            }
        """.trimIndent()
    )
}

@Composable
fun PercentageOfScreenDimensions() {
    Section(
        title = "Percentage of screen dimensions",
        description = """
            Dimension of the element are in percentage of fraction of the screen dimensions.
        """.trimIndent(),
        demo = {
            val configuration = LocalConfiguration.current

            Box(
                modifier = Modifier
                    .size(configuration.screenWidthDp.dp * 0.25f, configuration.screenHeightDp.dp * 0.25f)
                    .background(Color.Black)
            )
        },
        code = """
            val configuration = LocalConfiguration.current

            Box(
                modifier = Modifier
                    .size(configuration.screenWidthDp.dp * 0.25f, configuration.screenHeightDp.dp * 0.25f)
                    .background(Color.Black)
            )
        """.trimIndent()
    )
}

@Preview
@Composable
fun DimensionsAndConstraintsScreenPreview() {
    DimensionsAndConstraintsScreen(navController = rememberNavController())
}