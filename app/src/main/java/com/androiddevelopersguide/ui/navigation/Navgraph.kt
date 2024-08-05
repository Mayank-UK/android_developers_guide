package com.androiddevelopersguide.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androiddevelopersguide.ui.screens.postdetailscreen.PostDetailScreen
import com.androiddevelopersguide.ui.screens.postsscreen.PostsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = "posts", modifier) {
        composable("posts") {
            PostsScreen(onUserClick = { postId ->
                navController.navigate("post_detail/$postId")
            })
        }
        composable(
            "post_detail/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: return@composable
            PostDetailScreen(postId = postId)
        }
    }
}