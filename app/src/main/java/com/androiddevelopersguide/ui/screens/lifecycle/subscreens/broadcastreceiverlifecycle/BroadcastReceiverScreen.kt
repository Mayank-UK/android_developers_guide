package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.broadcastreceiverlifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.screens.lifecycle.LifecycleScreen

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

@Preview
@Composable
fun BroadcastReceiverScreenPreview() {
    BroadcastReceiverScreen(navController = rememberNavController())
}