package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase
) : ViewModel() {

    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadPost(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _post.value = getPostDetailUseCase(id)
            _isLoading.value = false
        }
    }
}