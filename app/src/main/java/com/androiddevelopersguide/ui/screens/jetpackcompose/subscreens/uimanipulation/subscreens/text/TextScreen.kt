package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.R

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun TextScreen(
    navController: NavController,
    viewModel: TextViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Text",
        mobileUI = {
            SimpleText()
            TextStyling()
            CustomFontFamily()
        }
    )
}

@Composable
fun SimpleText() {
    Section(
        title = "Simple text.",
        description = """
            Add simple text.
        """.trimIndent(),
        demo = {
            Text("Simple text")
        },
        code = """
            Text("Simple text")
        """.trimIndent()
    )
}

@Composable
fun TextStyling() {
    Section(
        title = "Add styling to the text.",
        description = """
            Add styling to the text.
        """.trimIndent(),
        demo = {
            Text(
                text = "Advanced Styling",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    letterSpacing = 2.sp,
                    lineHeight = 36.sp
                ),
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
        },
        code = """
            Text(
                text = "Advanced Styling",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    letterSpacing = 2.sp,
                    lineHeight = 36.sp
                ),
                modifier = Modifier
                    .background(Color.Blue)
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )
        """.trimIndent()
    )
}

@Composable
fun CustomFontFamily() {
    Section(
        title = "Use a particular font family.",
        description = """
            To add custom font. Create a android resource directory inside res/ and paste all the fonts inside it through android studio.
        """.trimIndent(),
        demo = {
            Text(
                text = "Montserrat Font Example",
                modifier = Modifier
                    .padding(16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular, FontWeight.Normal),
                        Font(R.font.montserrat_bold, FontWeight.Bold)
                    ),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
            )
        },
        code = """
            Text(
                text = "Montserrat Font Example",
                modifier = Modifier
                    .padding(16.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular, FontWeight.Normal),
                        Font(R.font.montserrat_bold, FontWeight.Bold)
                    ),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                ),
            )
        """.trimIndent()
    )
}

@Preview
@Composable
fun TextScreenPreview() {
    TextScreen(navController = rememberNavController())
}