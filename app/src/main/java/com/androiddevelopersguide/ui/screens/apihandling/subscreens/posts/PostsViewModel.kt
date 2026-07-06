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

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    /** Latest failure, if any. The screen routes it by severity (full-screen vs banner vs snackbar). */
    private val _failure = MutableStateFlow<Failure?>(null)
    val failure: StateFlow<Failure?> = _failure.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = getPostsUseCase()) {
                is Result.Success -> {
                    _posts.value = result.value
                    // A success may still carry a non-blocking warning (e.g. serving cached data
                    // because the network is down). Surface it; null clears any prior failure.
                    _failure.value = result.warning
                    _isLoading.value = false
                }
                is Result.Failed -> {
                    // Keep any previously loaded posts so a failed refresh can be non-blocking.
                    _failure.value = result.failure
                    _isLoading.value = false
                }
            }
        }
    }

    /** Called when a non-blocking error (banner/snackbar) is dismissed. */
    fun clearFailure() {
        _failure.value = null
    }
}
