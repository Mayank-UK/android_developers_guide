package com.androiddevelopersguide.ui.navigation

sealed class Screen(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object HomeScreen : Screen("home")
    object PostsScreen : Screen("posts")
    object PostDetailScreen : Screen("post_detail", objectName = "postId", objectPath = "/{postId}")
}