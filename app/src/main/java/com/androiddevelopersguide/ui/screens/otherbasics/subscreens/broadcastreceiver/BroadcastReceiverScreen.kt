package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.broadcastreceiver

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

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
            A Broadcast Receiver is a component in Android that allows an app to receive notifications from the Android system or other apps. It's a way for an app to react to events or messages sent by the system or other apps, without having to maintain a constant connection.
            
            Types of Broadcast Receivers:

            -Normal Broadcast Receiver: 
            Receives broadcasts synchronously, blocking the main thread until the receiver finishes processing the broadcast.
            
            -Ordered Broadcast Receiver: 
            Receives broadcasts synchronously, but allows multiple receivers to process the broadcast in a specific order.
            
            -Sticky Broadcast Receiver: 
            Receives broadcasts asynchronously, allowing the receiver to process the broadcast in the background.
            
            Broadcast Receivers are useful for a variety of scenarios, such as:

            -System events: 
            Receiving notifications about system events, like when the device is low on battery, or when a new SMS message is received.
            
            -App-to-app communication: 
            Allowing apps to communicate with each other, such as when an app wants to notify another app about a specific event.
            
            -Background tasks: 
            Running tasks in the background, like downloading data or performing maintenance tasks, in response to a broadcast.
            
            Note: Broadcast receiver needs to be declared in the manifest file and needs to be registered in the activity file.
        """.trimIndent(),
        demo = {
            Text("Toggle internet connection on and off to receive network status broadcast.")
        },
        code = """
            // NetworkConnectivityReceiver.kt
            class NetworkConnectivityReceiver : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val network = connectivityManager.activeNetwork
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

                    if (networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                        Toast.makeText(context, "Network connected", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Network disconnected", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            
            // MainActivity.kt
            @AndroidEntryPoint
            class MainActivity : ComponentActivity() {
                private lateinit var receiver: NetworkConnectivityReceiver

                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)

                    receiver = NetworkConnectivityReceiver()
                    registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

                    setContent {
                        AndroidDevelopersGuideTheme {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = androidx.compose.ui.graphics.Color.White
                            ) {
                                val navController = rememberNavController()
                                NavGraph(navController = navController)
                            }
                        }
                    }
                }

                override fun onDestroy() {
                    super.onDestroy()
                    unregisterReceiver(receiver)
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun BroadcastReceiverScreenPreview() {
    BroadcastReceiverScreen(navController = rememberNavController())
}