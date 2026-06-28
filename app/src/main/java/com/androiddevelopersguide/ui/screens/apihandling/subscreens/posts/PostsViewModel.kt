package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevelopersguide.core.Resource
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        getPostsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _posts.value = result.data
                    _isLoading.value = false
                    _error.value = null
                }
                is Resource.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                    _error.value = null
                }
            }
        }.launchIn(viewModelScope)
    }
}
