package com.androiddevelopersguide.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androiddevelopersguide.ui.theme.Sizes

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
            .background(color = Color(0xFFf2f2f2))
            .padding(Sizes.md)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(Sizes.sm))
        if(description != null) {
            Text(
                description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.height(Sizes.sm))
        if (demo != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xffdadada))
                    .padding(Sizes.md)
            ) {
                demo?.invoke()
            }
        }

        if (code != null) {
            TextButton(
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                onClick = { isExpanded = !isExpanded }
            ) {
                Text(
                    if (isExpanded) "Hide code" else "Show code",
                    color = Color.Blue,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Sizes.sm)
                ) {
                    item {
                        Text(
                            text = code,
                            modifier = Modifier
                                .background(Color(0xff303030), shape = MaterialTheme.shapes.large)
                                .padding(Sizes.md),
                            color = Color.White,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                                lineHeight = 20.sp,
                                letterSpacing = 2.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

