package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun PostDetailScreen(
    navController: NavController,
    viewModel: PostDetailViewModel = hiltViewModel(),
    postId: Int
) {
    val post by viewModel.post.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val configuration = LocalConfiguration.current

    // use LaunchedEffect if viewModel is already initialized and parameter is passed later, instead of init in viewModel
    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    PageScaffold(
        navController = navController,
        title = "Post detail Screen",
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
                post?.let { PostDetail(post = it) }
            }
        }
    )
}

@Composable
fun PostDetail(post : Post) {
    post.let { postItem ->
        Box(Modifier.padding(16.dp)) {
            Column(
                modifier = Modifier
                    .background(color = Color.Gray, shape = RoundedCornerShape(8.dp),)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    color = Color.White,
                    text = "Post title:\n${postItem.title}"
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    color = Color.White,
                    text ="Post body:\n${postItem.body}"
                )
            }
        }

    }
}

@Preview
@Composable
fun TempScreenPreview() {
    PostDetailScreen(navController = rememberNavController(), postId = 1)
}