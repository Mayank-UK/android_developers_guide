package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PostDetailScreen(
    viewModel: PostDetailViewModel = hiltViewModel(),
    postId: Int
) {
    val post by viewModel.post.collectAsState()

    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    post?.let { post ->
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${post.title}")
            Text("Email: ${post.body}")
        }
    }
}