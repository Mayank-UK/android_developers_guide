package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Activity",
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
            In Android, an Activity is a single screen that represents a user interface (UI) component of an application. It is a fundamental concept in Android app development, and it plays a crucial role in creating interactive and engaging user experiences.

            An Activity is essentially a window that contains a set of UI elements, such as buttons, text views, images, and other widgets, which are used to interact with the user. When an Activity is launched, it becomes the top-most window on the screen, and the user can interact with it by tapping, swiping, or typing.
            
            Here are some key characteristics of an Activity in Android:

            -Single screen: 
            An Activity represents a single screen or window that is displayed to the user.
            
            -UI component: 
            An Activity contains a set of UI elements, such as buttons, text views, and images, which are used to interact with the user.
            
            -Interactive: 
            An Activity is designed to be interactive, allowing the user to perform actions, such as tapping, swiping, or typing.
            
            -Lifecycle: 
            An Activity has a lifecycle, which includes creation, starting, resuming, pausing, stopping, and destroying. The Android system manages the lifecycle of an Activity.
            
            -Context: 
            An Activity has a Context, which provides access to the application's resources, such as the file system, network connections, and preferences.
            
            There are several types of Activities in Android, including:

            -MainActivity: 
            The main entry point of an application, which is launched when the user starts the app.
            
            -SubActivity: 
            A secondary Activity that is launched from another Activity.
            
            -DialogActivity: 
            A special type of Activity that displays a dialog window.
            
            -TransparentActivity: 
            A transparent Activity that allows the user to see the underlying Activity.
        """.trimIndent(),
        code = """
            @AndroidEntryPoint
            class MainActivity : ComponentActivity() {
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
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
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ActivityScreenPreview() {
    ActivityScreen(navController = rememberNavController())
}