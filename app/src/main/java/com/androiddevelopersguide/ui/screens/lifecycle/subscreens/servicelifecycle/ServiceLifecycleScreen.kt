package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.servicelifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ServiceLifecycleScreen(
    navController: NavController,
    viewModel: ServiceLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Service lifecycle",
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
            Here's an overview of the Service Lifecycle:

            1. onCreate()

            Called when the Service is created.
            The Service is initialized, and you can set up any necessary resources.
            
            2. onStartCommand(Intent, int, int)

            Called when the Service is started by an Intent.
            The Service can start executing its task, and you can return a value indicating how the Service should behave.
            
            You can return one of the following values:
            
            START_STICKY: The Service should continue running until it's explicitly stopped.
            
            START_NOT_STICKY: The Service should stop itself when it's done executing its task.
            
            START_REDELIVER_INTENT: The Service should redeliver the Intent to itself when it's restarted.
            
            3. onBind(Intent)

            Called when the Service is bound to a client (e.g., an Activity).
            The Service can return an IBinder object, which allows the client to interact with the Service.
            
            4. onUnbind(Intent)

            Called when the Service is unbound from a client.
            The Service can release any resources allocated for the client.
            
            5. onDestroy()

            Called when the Service is destroyed.
            The Service should release any resources and stop executing its task.
        """.trimIndent(),
        code = """
            public class DownloadService extends Service {
                private DownloadTask task;

                @Override
                public void onCreate() {
                    super.onCreate();
                    // Initialize resources
                }

                @Override
                public int onStartCommand(Intent intent, int flags, int startId) {
                    // Start the download task
                    task = new DownloadTask();
                    task.execute(intent.getStringExtra("url"));
                    return START_STICKY;
                }

                @Override
                public IBinder onBind(Intent intent) {
                    // Not used in this example
                    return null;
                }

                @Override
                public boolean onUnbind(Intent intent) {
                    // Not used in this example
                    return false;
                }

                @Override
                public void onDestroy() {
                    super.onDestroy();
                    // Stop the download task
                    task.cancel(true);
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ServiceLifecycleScreenPreview() {
    ServiceLifecycleScreen(navController = rememberNavController())
}