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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

@Composable
fun PostDetailScreen(
    navController: NavController,
    viewModel: PostDetailViewModel = hiltViewModel(),
    postId: Int
) {
    val uiState by viewModel.uiState.collectAsState()

    // use LaunchedEffect if viewModel is already initialized and parameter is passed later, instead of init in viewModel
    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    PageScaffold(
        navController = navController,
        title = "Post detail Screen",
        mobileUI = {
            val currentFailure = uiState.failure
            val currentPost = uiState.post
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                // Blocking: no post to show and an error occurred.
                currentFailure != null && currentPost == null -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ErrorView(
                            failure = currentFailure,
                            onRetry = { viewModel.loadPost(postId) }
                        )
                    }
                }
                currentPost != null -> {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Non-blocking: we have data, surface the failure transiently by severity.
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
                        PostDetail(post = currentPost)
                    }
                }
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
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainer,
                        shape = RoundedCornerShape(8.dp),
                    )
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Post title:\n${postItem.title}"
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Post body:\n${postItem.body}"
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
