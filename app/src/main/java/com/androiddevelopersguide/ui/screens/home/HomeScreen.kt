package com.androiddevelopersguide.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.BuildConfig
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.activities.XmlLayoutActivity
import com.androiddevelopersguide.ui.components.LinkCard
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.WindowWidthSizeClass
import com.androiddevelopersguide.ui.components.rememberWindowSize
import com.androiddevelopersguide.ui.theme.Sizes

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val windowSize = rememberWindowSize()
    val columns = when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        WindowWidthSizeClass.Expanded -> 3
    }

    PageScaffold(
        navController = navController,
        title = "Android Developer's Guide",
        mobileUI = {
            HomeContent(
                categories = viewModel.groupedLinks,
                columns = columns,
                onLinkClick = { link ->
                    if (link.routePath == HomeViewModel.XML_LAYOUT_ROUTE) {
                        context.startActivity(Intent(context, XmlLayoutActivity::class.java))
                    } else {
                        navController.navigate(link.routePath)
                    }
                }
            )
        }
    )
}

@Composable
private fun HomeContent(
    categories: List<LinkCategory>,
    columns: Int,
    onLinkClick: (LinkData) -> Unit,
) {
    EnvironmentBadge()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Sizes.md),
        verticalArrangement = Arrangement.spacedBy(Sizes.md)
    ) {
        HeroIntro()

        categories.forEach { category ->
            CategoryHeader(title = category.title)
            // Lay out the category's cards in fixed-width rows for responsiveness.
            category.links.chunked(columns).forEach { rowLinks ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Sizes.md)
                ) {
                    rowLinks.forEach { link ->
                        LinkCard(
                            link = link,
                            onClick = { onLinkClick(link) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    // Pad the trailing row so cards keep consistent widths.
                    repeat(columns - rowLinks.size) {
                        Spacer(Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(Modifier.height(Sizes.lg))
    }
}

@Composable
private fun HeroIntro() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Android Developer's Guide",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(Sizes.xs))
        Text(
            text = "A hands-on, runnable reference for modern Android development with Kotlin and Jetpack Compose — each topic is a small working example.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun CategoryHeader(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
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
