package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.supportingclassesandenums

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun SupportingClassesAndEnumsScreen(
    navController: NavController,
    viewModel: SupportingClassesAndEnumsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Supporting classes & enums",
            )
        },
        mobileUI = {
            Basics()
            InlineClasses()
            Enums()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            These are the classes and enums used throughout the application that holds certain data to be used easily.
            
            At the time of development, these are the available.
        """.trimIndent(),
    )
}

@Composable
fun InlineClasses() {
    Section(
        title = "Inline classes",
        description = """
            Some inline classes also provide constant values like enums.
            
            Here's a list of some most used inline classes:
            -Color
            -TextUnit
            -Dp
            -DpOffset
            -IntOffset
            -IntSize
            -Density
            -FontFamily
        """.trimIndent(),
        demo = {
            Box(
                Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            )
        },
        code = """
            Box(
                Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .width(64.dp)
            )
        """.trimIndent()
    )
}

@Composable
fun Enums() {
    Section(
        title = "Enums",
        description = """
            In jetpack compose, there are several predefined enums that are commonly used for various UI-related configurations.
            
            Here's a list of some of the most frequently used predefined enums in jetpack compose:
            -Alignment
            -Arrangement
            -SelectionMode
            -LayoutDirection
            -TextAlign
            -TextDecoration
            -TextUnit
            -FontWeight
            -FontStyle
            -KeyboardType
            -ImeAction
            -VisualTransformation
            -ScrollbarStyle
            -FocusDirection
            -FocusRequester.CreateReason
            -DragDirection
            -Orientation
            -PaddingValues
            -LazyListState
            -DrawStyle
            -FilterQuality
            -TileMode
            -BlendMode
            -CompositingStrategy
            -PointerIcon
            -HorizontalAlignment
            -VerticalAlignment
        """.trimIndent(),
        demo = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
            }
        },
        code = """
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(64.dp)
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    Modifier
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
fun SupportingClassesAndEnumsScreenPreview() {
    SupportingClassesAndEnumsScreen(navController = rememberNavController())
}