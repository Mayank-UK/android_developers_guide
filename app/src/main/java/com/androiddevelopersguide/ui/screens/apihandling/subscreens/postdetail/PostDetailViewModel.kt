package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevelopersguide.core.Resource
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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadPost(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            when (val result = getPostDetailUseCase(id)) {
                is Resource.Success -> {
                    _post.value = result.data
                    _isLoading.value = false
                }
                is Resource.Error -> {
                    _error.value = result.message
                    _isLoading.value = false
                }
                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }
}
