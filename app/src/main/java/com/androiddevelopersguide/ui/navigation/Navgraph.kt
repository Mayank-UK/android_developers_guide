package com.androiddevelopersguide.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androiddevelopersguide.ui.screens.api.subscreens.postdetail.PostDetailScreen
import com.androiddevelopersguide.ui.screens.api.subscreens.posts.PostsScreen
import com.androiddevelopersguide.ui.screens.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = Screen.HomeScreen.route, modifier) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.PostsScreen.route) {
            PostsScreen(onUserClick = { postId ->
                navController.navigate("${Screen.PostDetailScreen.route}/$postId")
            })
        }

        composable(
            Screen.PostDetailScreen.route + Screen.PostDetailScreen.objectPath,
            arguments = listOf(navArgument(Screen.PostDetailScreen.objectName) { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt(Screen.PostDetailScreen.objectName) ?: return@composable
            PostDetailScreen(postId = postId)
        }
    }
}