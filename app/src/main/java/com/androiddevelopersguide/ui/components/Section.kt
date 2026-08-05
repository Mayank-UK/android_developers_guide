package com.androiddevelopersguide.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androiddevelopersguide.ui.theme.Sizes

/**
 * A titled content section card. Mirrors the React guide's section treatment: a surface-toned
 * card with a hairline [MaterialTheme.colorScheme.outline] border and the section title preceded
 * by a short indigo [MaterialTheme.colorScheme.primary] accent bar.
 *
 * All fills adapt to the active theme (no hardcoded grayscale):
 *  - card background  -> `surfaceContainer`
 *  - demo box fill    -> `surfaceContainerHigh` (a slightly raised surface)
 *  - code block fill  -> `surfaceContainerHighest`, with `onSurface` text for readable contrast
 *    in both light and dark.
 */
@Composable
fun Section(
    title: String,
    description: String? = null,
    demo: (@Composable () -> Unit)? = null,
    code: String? = null,
) {
    var isExpanded: Boolean by remember { mutableStateOf(false) }

    Spacer(Modifier.height(Sizes.md))
    Column(
        Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.large
            )
            .padding(Sizes.md)
    ) {
        SectionHeader(title = title)
        if (description != null) {
            Spacer(Modifier.height(Sizes.sm))
            Text(
                description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.height(Sizes.sm))
        if (demo != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(Sizes.md)
            ) {
                demo.invoke()
            }
        }

        if (code != null) {
            TextButton(
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                onClick = { isExpanded = !isExpanded }
            ) {
                Text(
                    if (isExpanded) "Hide code" else "Show code",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Sizes.sm)
                ) {
                    item {
                        Text(
                            text = code,
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.large)
                                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                                .padding(Sizes.md),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 20.sp,
                                letterSpacing = 0.5.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(width = 3.dp, height = Sizes.md)
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(Modifier.width(Sizes.sm))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
