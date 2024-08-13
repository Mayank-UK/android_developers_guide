package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.iconsandimages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.R
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun IconsAndImagesScreen(
    navController: NavController,
    viewModel: IconsAndImagesViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Icons & images",
            )
        },
    ) {
        CustomIcons()
        CustomImages()
    }
}

@Composable
fun CustomIcons() {
    Section(
        title = "Icons",
        description = """
            Show icons.
        """.trimIndent(),
        demo = {
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Thumb Up",
                modifier = Modifier.size(24.dp)
            )
        },
        code = """
            Icon(
                imageVector = Icons.Filled.ThumbUp,
                contentDescription = "Thumb Up",
                modifier = Modifier.size(24.dp)
            )
        """.trimIndent()
    )
}

@Composable
fun CustomImages() {
    Section(
        title = "Images",
        description = """
            First add the image in res/drawable to display it on the screen.
        """.trimIndent(),
        demo = {
            Image(
                painter = painterResource(R.drawable.my_image),
                contentDescription = "My Image",
                modifier = Modifier.size(128.dp, 128.dp)
            )
        },
        code = """
            Image(
                painter = painterResource(R.drawable.my_image),
                contentDescription = "My Image",
                modifier = Modifier.size(128.dp, 128.dp)
            )
        """.trimIndent()
    )
}

@Preview
@Composable
fun IconsAndImagesScreenPreview() {
    IconsAndImagesScreen(navController = rememberNavController())
}