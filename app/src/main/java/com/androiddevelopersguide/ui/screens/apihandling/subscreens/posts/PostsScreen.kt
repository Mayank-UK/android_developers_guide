package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.core.failures.FailureSeverity
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.error.ErrorBanner
import com.androiddevelopersguide.ui.components.error.ErrorSnackbar
import com.androiddevelopersguide.ui.components.error.ErrorView
import com.androiddevelopersguide.ui.navigation.NavRouter

@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: PostsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PageScaffold(
        navController = navController,
        title = "Posts Screen",
        // The posts list is itself a lazy scrolling surface; disable the scaffold's
        // outer verticalScroll so the inner LazyColumn doesn't land in a scrolling
        // parent (infinite-height constraint).
        scrollable = false,
        appBarActions = {
            IconButton(onClick = { viewModel.loadPosts() }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        },
        mobileUI = {
            val posts = uiState.posts
            val currentFailure = uiState.failure
            when {
                // Blocking: nothing to show and a critical/error state.
                currentFailure != null && posts.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ErrorView(
                            failure = currentFailure,
                            onRetry = { viewModel.loadPosts() }
                        )
                    }
                }

                uiState.isLoading && posts.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Non-blocking: we still have data, so surface the failure transiently.
                        currentFailure?.let { nonBlockingFailure ->
                            when (nonBlockingFailure.severity) {
                                FailureSeverity.INFO -> ErrorSnackbar(
                                    failure = nonBlockingFailure,
                                    onDismiss = { viewModel.clearFailure() }
                                )
                                FailureSeverity.WARNING, FailureSeverity.CRITICAL -> ErrorBanner(
                                    failure = nonBlockingFailure,
                                    onDismiss = { viewModel.clearFailure() }
                                )
                            }
                        }
                        if (posts.isNotEmpty()) {
                            Posts(posts = posts, navController = navController)
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun Posts(posts: List<Post>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(items = posts, key = { it.id }) { post ->
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                text = post.title,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(8.dp),
                    )
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
