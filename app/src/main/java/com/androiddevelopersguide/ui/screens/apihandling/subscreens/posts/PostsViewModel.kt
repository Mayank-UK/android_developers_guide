package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // this is a common pattern to have two variables
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // use init if viewModel do not depend on any parameter from view
    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _isLoading.value = true
            _posts.value = getPostsUseCase()
            _isLoading.value = false
        }
    }
}