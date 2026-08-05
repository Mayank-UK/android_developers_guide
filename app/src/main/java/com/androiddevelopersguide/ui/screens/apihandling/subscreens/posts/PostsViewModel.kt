package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevelopersguide.core.failures.Failure
import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Consolidated UI state for the Posts screen. A single source of truth avoids the
 * interleaving bugs that arise from observing three independent flows.
 */
data class PostsUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val failure: Failure? = null
)

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            // Clear any prior failure and mark loading at the start of the refresh.
            _uiState.value = _uiState.value.copy(isLoading = true, failure = null)
            when (val result = getPostsUseCase()) {
                is Result.Success -> {
                    _uiState.value = PostsUiState(
                        posts = result.value,
                        isLoading = false,
                        // A success may still carry a non-blocking warning (e.g. serving cached
                        // data because the network is down). Surface it; null clears any prior failure.
                        failure = result.warning
                    )
                }
                is Result.Failed -> {
                    // Keep any previously loaded posts so a failed refresh can be non-blocking.
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        failure = result.failure
                    )
                }
            }
        }
    }

    /** Called when a non-blocking error (banner/snackbar) is dismissed. */
    fun clearFailure() {
        _uiState.value = _uiState.value.copy(failure = null)
    }
}
