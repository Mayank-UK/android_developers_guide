package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import app.cash.turbine.test
import com.androiddevelopersguide.core.failures.ServerFailure
import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostDetailViewModelTest {

    private val getPostDetailUseCase: GetPostDetailUseCase = mockk()
    private lateinit var viewModel: PostDetailViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadPost should update state on success`() = runTest {
        val post = Post(1, 1, "Title", "Body")
        coEvery { getPostDetailUseCase(1) } returns Result.Success(post)

        viewModel = PostDetailViewModel(getPostDetailUseCase)
        viewModel.loadPost(1)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(post, state.post)
            assertEquals(false, state.isLoading)
            assertNull(state.failure)
        }
    }

    @Test
    fun `loadPost should update state on error`() = runTest {
        val failure = ServerFailure("Error loading post")
        coEvery { getPostDetailUseCase(1) } returns Result.Failed(failure)

        viewModel = PostDetailViewModel(getPostDetailUseCase)
        viewModel.loadPost(1)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(failure, state.failure)
            assertEquals(false, state.isLoading)
            assertNull(state.post)
        }
    }
}
