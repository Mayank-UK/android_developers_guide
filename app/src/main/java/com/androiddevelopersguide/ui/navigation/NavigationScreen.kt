package com.androiddevelopersguide.ui.navigation

sealed class Screen(
    val route: String,
    val title: String = "",
    val objectName: String = "",
    val objectPath: String = ""
) {
    object Posts : Screen("posts")
    object PostDetail : Screen("post_detail", objectName = "postId", objectPath = "/{postId}")
}