package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.visibility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun VisibilityScreen(
    navController: NavController,
    viewModel: VisibilityViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Visibility",
            )
        },
        mobileUI = {
            Visibility()
        }
    )
}

@Composable
fun Visibility() {
    Section(
        title = "Visibility",
        description = """
            Hide or show element based on requirement.
            
            There is no element or modifier to directly make changes to the elements visibility (not considering 0 opacity). Element can be shown or hidden based on condition.
        """.trimIndent(),
        demo = {
            var isVisible by remember { mutableStateOf(true) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { isVisible = !isVisible }) {
                    Text("Toggle visibility")
                }
                Spacer(Modifier.width(32.dp))
                if (isVisible) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .height(32.dp)
                            .width(64.dp)
                    )
                }
            }
        },
        code = """
            var isVisible by remember { mutableStateOf(true) }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { isVisible = !isVisible }) {
                    Text("Toggle visibility")
                }
                Spacer(Modifier.width(32.dp))
                if (isVisible) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .height(32.dp)
                            .width(64.dp)
                    )
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun VisibilityScreenPreview() {
    VisibilityScreen(navController = rememberNavController())
}