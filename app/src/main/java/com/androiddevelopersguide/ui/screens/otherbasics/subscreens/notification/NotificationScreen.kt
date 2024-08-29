package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: NotificationViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Temp Screen",
        mobileUI = {
            Basics()
        }
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            To show a simple notification in an Android app using Jetpack Compose.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current

            var permissionGranted by remember { mutableStateOf(false) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                permissionGranted = isGranted
            }

            @RequiresApi(Build.VERSION_CODES.O)
            fun createNotificationChannel(context: Context) {
                val name = "My Channel"
                val descriptionText = "This is my notification channel"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("my_channel_id", name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel(context)
                }

                permissionGranted = ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (!permissionGranted) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            fun showNotification(context: Context) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val builder = NotificationCompat.Builder(context, "my_channel_id")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("My Notification")
                        .setContentText("Hello! This is a notification.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                    val notificationManager = NotificationManagerCompat.from(context)
                    notificationManager.notify(1, builder.build())
                } else {
                    Log.e("Notification", "Permission not granted to show notifications.")
                }
            }

            Button(
                onClick = {
                    if (permissionGranted) {
                        showNotification(context)
                    } else {
                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            ) {
                Text("Show Notification")
            }
        },
        code = """
            val context = LocalContext.current

            var permissionGranted by remember { mutableStateOf(false) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                permissionGranted = isGranted
            }

            @RequiresApi(Build.VERSION_CODES.O)
            fun createNotificationChannel(context: Context) {
                val name = "My Channel"
                val descriptionText = "This is my notification channel"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("my_channel_id", name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            LaunchedEffect(Unit) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel(context)
                }

                permissionGranted = ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED

                if (!permissionGranted) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            fun showNotification(context: Context) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val builder = NotificationCompat.Builder(context, "my_channel_id")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("My Notification")
                        .setContentText("Hello! This is a notification.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                    val notificationManager = NotificationManagerCompat.from(context)
                    notificationManager.notify(1, builder.build())
                } else {
                    Log.e("Notification", "Permission not granted to show notifications.")
                }
            }

            Button(
                onClick = {
                    if (permissionGranted) {
                        showNotification(context)
                    } else {
                        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                }
            ) {
                Text("Show Notification")
            }
        """.trimIndent()
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(navController = rememberNavController())
}