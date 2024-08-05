package com.androiddevelopersguide.ui.screens.postsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.androiddevelopersguide.domain.models.Post

@Composable
fun PostsScreen(
    viewModel: PostsViewModel = hiltViewModel(),
    onUserClick: (Int) -> Unit
) {
    val posts by viewModel.posts.collectAsState()

    LazyColumn {
        items(posts) { post ->
            PostItem(post, onUserClick)
        }
    }
}

@Composable
fun PostItem(post: Post, onUserClick: (Int) -> Unit) {
    Text(
        text = post.title,
        modifier = Modifier
            .clickable { onUserClick(post.id) }
            .padding(16.dp)
    )
}