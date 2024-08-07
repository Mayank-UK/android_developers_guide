package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.broadcastreceiverlifecycle

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun BroadcastReceiverScreen(
    navController: NavController,
    viewModel: BroadcaseReceiverViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Broadcast receiver lifecycle",
            )
        },
    ) {

    }
}