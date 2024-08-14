package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun NavigationScreen(
    navController: NavController,
    viewModel: NavigationViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Navigation",
            )
        },
        mobileUI = {
            JetpackComposeNavigation()
        }
    )
}

@Composable
fun JetpackComposeNavigation() {
    Section(
        title = "Compose navigation library",
        description = """
            Using this library, you define a NavHost, NavController, and a NavGraph to handle different routes and screens in your app.
            
            Put the NavHost and NavGraph together in a separate file. Also, put all the routes in a different file.
            
            Then use this NavGraph file inside MainActivity to add routing to your application.
            
            Finally use navController to navigate between different screens.
        """.trimIndent(),
        code = """
            // NavGraph file
            @Composable
            fun NavGraph(
                navController: NavHostController,
                modifier: Modifier = Modifier
            ) {
                NavHost(navController, startDestination = NavRouter.HomeScreen.route, modifier) {
                    composable(NavRouter.TempScreen.route) { TempScreen(navController = navController) }

                    composable(NavRouter.HomeScreen.route) { HomeScreen(navController = navController) }
                }
            }
            
            // NavRouter file
            sealed class NavRouter(
                val route: String,
                val objectName: String = "",
                val objectPath: String = ""
            ) {
                object TempScreen : NavRouter("temp")

                object HomeScreen : NavRouter("home")
            }
            
            // MainActivity file
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
            
            // use navController to navigate between different screens
            navController.navigate(NavRouter.HomeScreen.route)
        """.trimIndent()
    )
}

@Preview
@Composable
fun NavigationScreenPreview() {
    NavigationScreen(navController = rememberNavController())
}