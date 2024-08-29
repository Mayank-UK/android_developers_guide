package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.alignment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun AlignmentScreen(
    navController: NavController,
    viewModel: AlignmentViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Alignment",
        mobileUI = {
            BoxAlignment()
            ColumnLayoutAlignment()
            RowLayoutAlignment()
            TextAlignment()
        }
    )
}

@Composable
fun BoxAlignment() {
    Section(
        title = "Alignment in box",
        description = """
            Alignment of the box children.
        """.trimIndent(),
        demo = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .padding(8.dp)
                    .width(128.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .height(32.dp)
                        .width(32.dp)
                )
            }
        },
        code = """
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(32.dp)
                    .padding(8.dp)
                    .width(128.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .height(32.dp)
                        .width(32.dp)
                )
            }
        """.trimIndent()
    )
}

@Composable
fun ColumnLayoutAlignment() {
    Section(
        title = "Alignment in other layout elements",
        description = """
            Alignment of the column and lazyColumn children.
        """.trimIndent(),
        demo = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
            }
        },
        code = """
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
            }
        """.trimIndent()
    )
}

@Composable
fun RowLayoutAlignment() {
    Section(
        title = "Alignment in other layout elements",
        description = """
            Alignment of the row and lazyRow children.
        """.trimIndent(),
        demo = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
            }
        },
        code = """
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
                Spacer(Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .height(32.dp)
                        .width(128.dp),
                )
            }
        """.trimIndent()
    )
}

@Composable
fun TextAlignment() {
    Section(
        title = "Text alignment",
        description = """
            Align the text.
        """.trimIndent(),
        demo = {
            Text(
                text = "Hello, Jetpack Compose!",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        code = """
            Text(
                text = "Hello, Jetpack Compose!",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        """.trimIndent()
    )
}

@Preview
@Composable
fun AlignmentScreenPreview() {
    AlignmentScreen(navController = rememberNavController())
}