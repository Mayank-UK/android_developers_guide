package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.service

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.services.PlaySoundService

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import com.androiddevelopersguide.util.isServiceRunning

@Composable
fun ServiceScreen(
    navController: NavController,
    viewModel: ServiceViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Service",
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
            In Android, a Service is a component that can perform long-running operations in the background. It does not have a user interface and can run even when the user is not interacting with the application.
            
            There are two types of services in Android: 
            
            -Started services:
             A started service is a service that is started by calling startService() and can run indefinitely.
             
            -Bound services:
             A bound service is a service that is bound to a component, such as an activity, and can be used to communicate with the component.
             
             Note: Need to declare the service in manifest file.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current
            val isServiceRunning = remember {
                mutableStateOf(isServiceRunning(context, PlaySoundService::class.java.name))
            }

            Column{
                Text("This service will play sound in the background")
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isServiceRunning.value) {
                        Button(onClick = {
                            context.stopService(Intent(context, PlaySoundService::class.java))
                            isServiceRunning.value = false
                        }) {
                            Text("Stop service")
                        }
                    } else {
                        Button(onClick = {
                            context.startService(Intent(context, PlaySoundService::class.java))
                            isServiceRunning.value = true
                        }) {
                            Text("Start service")
                        }
                    }
                }
            }
        },
        code = """
            val context = LocalContext.current
            val isServiceRunning = remember {
                mutableStateOf(isServiceRunning(context, PlaySoundService::class.java.name))
            }
            
            fun isServiceRunning(context: Context, serviceName: String): Boolean {
                val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)
                for (service in runningServices) {
                    if (service.service.className == serviceName) {
                        return true
                    }
                }
                return false
            }

            Column{
                Text("This service will play sound in the background")
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isServiceRunning.value) {
                        Button(onClick = {
                            context.stopService(Intent(context, PlaySoundService::class.java))
                            isServiceRunning.value = false
                        }) {
                            Text("Stop service")
                        }
                    } else {
                        Button(onClick = {
                            context.startService(Intent(context, PlaySoundService::class.java))
                            isServiceRunning.value = true
                        }) {
                            Text("Start service")
                        }
                    }
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ServiceScreenPreview() {
    ServiceScreen(navController = rememberNavController())
}