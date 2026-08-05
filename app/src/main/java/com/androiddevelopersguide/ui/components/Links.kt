package com.androiddevelopersguide.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.theme.Sizes

/**
 * A vertically stacked list of descriptive [LinkCard]s that navigate via [navController].
 * Renders a [LinkData.description]/icon when present and degrades to a quiet title card
 * otherwise, so every screen that uses `Links` gets the dark-mode-aware treatment.
 */
@Composable
fun Links(
    links: List<LinkData>,
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Sizes.md, vertical = Sizes.lg),
        verticalArrangement = Arrangement.spacedBy(Sizes.md)
    ) {
        links.forEach { link ->
            LinkCard(
                link = link,
                onClick = { navController.navigate(link.routePath) }
            )
        }
    }
}
