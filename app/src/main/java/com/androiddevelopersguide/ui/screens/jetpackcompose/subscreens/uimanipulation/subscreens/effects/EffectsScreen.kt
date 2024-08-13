package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.effects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
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
fun EffectsScreen(
    navController: NavController,
    viewModel: EffectsViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Effects",
            )
        },
    ) {
        Shadow()
        Opacity()
    }
}

@Composable
fun Shadow() {
    Section(
        title = "Shadow",
        description = """
            Shadow makes an element appear as 3D.
            
            Shadow seem to work properly only if is applied through Surface element as parent.
        """.trimIndent(),
        demo = {
            Surface(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(4.dp),
                        clip = true
                    ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Box(
                    Modifier
                        .background(Color.White)
                        .height(64.dp)
                        .width(128.dp)
                )
            }
        },
        code = """
            Surface(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(4.dp),
                        clip = true
                    ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Box(
                    Modifier
                        .background(Color.White)
                        .height(64.dp)
                        .width(128.dp)
                )
            }
        """.trimIndent()
    )
}

@Composable
fun Opacity() {
    Section(
        title = "Opacity",
        description = """
            Opacity controls the visibility of an element.
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
fun EffectsScreenPreview() {
    EffectsScreen(navController = rememberNavController())
}