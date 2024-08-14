package com.androiddevelopersguide.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PageScaffold(
    topBar: @Composable () -> Unit,
    mobileUI: @Composable () -> Unit,
    tabletUI: (@Composable () -> Unit)? = null,
    desktopUI: (@Composable () -> Unit)? = null,
) {
    Scaffold(
        topBar = topBar,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            ResponsiveLayout(
                mobileUI = { mobileUI() },
                tabletUI = {
                    if (tabletUI != null) {
                        tabletUI()
                    } else {
                        mobileUI()
                    }
                },
                desktopUI = {
                    if (desktopUI != null) {
                        desktopUI()
                    } else {
                        mobileUI()
                    }
                }
            )
        }


    }
}