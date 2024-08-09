package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.broadcastreceiver

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold

@Composable
fun BroadcastReceiverScreen(
    navController: NavController,
    viewModel: BroadcastReceiverViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Broadcast receiver",
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