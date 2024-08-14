package com.androiddevelopersguide.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.theme.Sizes

@Composable
fun Links(
    links: List<LinkData>,
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = Sizes.md, vertical = Sizes.lg),
        verticalArrangement = Arrangement.spacedBy(Sizes.md)
    ) {
        links.forEach { link ->
            Text(
                text = link.title,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .clickable(onClick = { navController.navigate(link.routePath) })
                    .padding(Sizes.md),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}