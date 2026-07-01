package com.androiddevelopersguide.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.androiddevelopersguide.BuildConfig
import com.androiddevelopersguide.ui.components.Links
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.activities.XmlLayoutActivity
import com.androiddevelopersguide.ui.theme.Sizes

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    PageScaffold(
        navController = navController,
        title = "Android Developer's Guide",
        mobileUI = {
            EnvironmentBadge()
            Links(
                links = viewModel.links1,
                navController = navController
            )
            Text(
                text = "XML layout",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(Sizes.md)
                    .offset(y = (-Sizes.lg))
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(onClick = {
                        val intent = Intent(context, XmlLayoutActivity::class.java)
                        context.startActivity(intent)
                    })
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(Sizes.md),
                style = MaterialTheme.typography.bodyMedium
            )
            Box(Modifier.offset(y = (-Sizes.xl))) {
                Links(
                    links = viewModel.links2,
                    navController = navController
                )
            }
        }
    )
}

@Composable
fun EnvironmentBadge() {
    val flavor = BuildConfig.FLAVOR
    if (flavor != "prod") {
        val (backgroundColor, textColor, label) = when (flavor) {
            "dev" -> Triple(Color.Red, Color.White, "DEV - FAKE DATA")
            "staging" -> Triple(Color(0xFFFFA500), Color.Black, "STAGING")
            else -> Triple(Color.Gray, Color.White, flavor.uppercase())
        }

        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(vertical = Sizes.xs),
            color = textColor,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}