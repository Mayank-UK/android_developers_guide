package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.intent

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.activities.MainActivity
import com.androiddevelopersguide.ui.activities.TempActivity

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun IntentScreen(
    navController: NavController,
    viewModel: IntentViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Intent",
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
            In Android, an Intent is a messaging object that is used to request an action from an app component, such as an Activity, Service, or BroadcastReceiver. Intents are used to communicate between different components of an app, as well as between different apps.

            An Intent is essentially a request to perform an action, and it can be thought of as a "message" that is sent to the Android system, which then determines which app component can handle the request.
            
            There are two types of Intents:

            -Explicit Intent: 
            Specifies a specific component to handle the request, using the component's class name.
            
            -Implicit Intent: 
            Does not specify a specific component, allowing the Android system to determine which component can handle the request, based on the action and data specified in the Intent.
            
            Here are some key characteristics of Intents:

            -Requesting an action: 
            An Intent requests an action to be performed, such as "display a webpage" or "send an email".
            
            -Decoupling: 
            Intents decouple the requester of an action from the provider of the action, allowing for loose coupling between components.
            
            -Asynchronous: 
            Intents are asynchronous, meaning that the requester does not wait for the provider to complete the action.
            
            -Explicit or implicit: 
            Intents can be explicit, specifying a specific component to handle the request, or implicit, allowing the Android system to determine which component can handle the request.
            
            Note: After creating an activity, it needs to be added to manifest file
        """.trimIndent(),
        demo = {
            val context = LocalContext.current

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
                        context.startActivity(intent)
                    }) {
                        Text("Open Website")
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "(Implicit intent)",
                        style = MaterialTheme.typography.bodySmall
                    )

                }
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {
                        val intent = Intent(context, TempActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Text("Start TempActivity")
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "(Explicit intent)",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        },
        code = """
            // TempActivity.kt
            class TempActivity : ComponentActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_temp)
                }
            }
            
            val context = LocalContext.current

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.example.com"))
                        context.startActivity(intent)
                    }) {
                        Text("Open Website")
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "(Implicit intent)",
                        style = MaterialTheme.typography.bodySmall
                    )

                }
                Spacer(Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = {
                        val intent = Intent(context, TempActivity::class.java)
                        context.startActivity(intent)
                    }) {
                        Text("Start TempActivity")
                    }
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "(Explicit intent)",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun IntentScreenPreview() {
    IntentScreen(navController = rememberNavController())
}