package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.workmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun WorkManagerScreen(
    navController: NavController,
    viewModel: WorkManagerViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Work manager",
            )
        },
        mobileUI = {
            Basics(viewModel = viewModel)
        }
    )
}

@Composable
fun Basics(viewModel: WorkManagerViewModel) {
    val count by viewModel.counter.collectAsState()

    Section(
        title = "Work manager",
        description = """
            WorkManager is designed for deferrable and guaranteed background work. In this example, 
            we're using it to increment a counter every 15 minutes, even if the app is closed or the 
            device restarts. This demonstrates WorkManager's ability to persist and execute tasks 
            across app or device restarts.
        """.trimIndent(),
        demo = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "The counter will increment every 15 minutes, even if the app is closed. There is no error in the console or anywhere, but it don't seem to work.", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Counter: $count", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.startCounter() }) {
                    Text(text = "Start")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.stopCounter() }) {
                    Text(text = "Stop")
                }
            }
        },
        code = """
            // Add your code snippet after successfully testing the workmanager
        """.trimIndent()
    )
}

@Preview
@Composable
fun WorkManagerScreenPreview() {
    WorkManagerScreen(navController = rememberNavController())
}