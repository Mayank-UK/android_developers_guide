package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevelopersguide.core.failures.Failure
import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Consolidated UI state for the Post detail screen. Keeps the typed [Failure] (instead of
 * downgrading to a bare String) so severity/retry semantics stay consistent with PostsScreen.
 */
data class PostDetailUiState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    val failure: Failure? = null
)

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostDetailUiState())
    val uiState: StateFlow<PostDetailUiState> = _uiState.asStateFlow()

    fun loadPost(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, failure = null)
            when (val result = getPostDetailUseCase(id)) {
                is Result.Success -> {
                    _uiState.value = PostDetailUiState(
                        post = result.value,
                        isLoading = false,
                        failure = result.warning
                    )
                }
                is Result.Failed -> {
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
