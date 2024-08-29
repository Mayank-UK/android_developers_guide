package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.domain.models.Post

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.navigation.NavRouter

@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val configuration = LocalConfiguration.current

    PageScaffold(
        navController = navController,
        title = "Posts Screen",
        mobileUI = {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(configuration.screenHeightDp.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                Posts(posts = posts, navController = navController)
            }

        }
    )
}

@Composable
fun Posts(posts: List<Post>, navController: NavController) {
    val configuration = LocalConfiguration.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(configuration.screenHeightDp.dp)
            .padding(16.dp)
    ) {
        items(posts) { post ->
            Text(
                color = Color.White,
                text = post.title,
                modifier = Modifier
                    .background(color = Color.Gray, shape = RoundedCornerShape(8.dp),)
                    .clickable {
                        navController.navigate("${NavRouter.PostDetailScreen.route}/${post.id}")
                    }
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun TempScreenPreview() {
    PostsScreen(navController = rememberNavController())
}