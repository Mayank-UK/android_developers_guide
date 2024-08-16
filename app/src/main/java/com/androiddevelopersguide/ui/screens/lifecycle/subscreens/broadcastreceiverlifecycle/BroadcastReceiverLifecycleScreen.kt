package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.broadcastreceiverlifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun BroadcastReceiverLifecycleScreen(
    navController: NavController,
    viewModel: BroadcastReceiverViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Broadcast receiver lifecycle",
            )
        },
        mobileUI = {
            Basics()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            Unlike Activities, Services, and Fragments, which have multiple lifecycle methods, a Broadcast Receiver has only one lifecycle method.
            
            -onReceive(Context, Intent)

            This method is called when the system sends a broadcast Intent to the receiver. The receiver must process the Intent and return from this method as quickly as possible, as it runs on the main thread of the app.
        """.trimIndent(),
        code = """
            public class BatteryLowReceiver extends BroadcastReceiver {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
                        Log.d("BatteryLowReceiver", "Battery level is low!");
                        // Perform actions when battery level is low
                    }
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun BroadcastReceiverLifecycleScreenPreview() {
    BroadcastReceiverLifecycleScreen(navController = rememberNavController())
}