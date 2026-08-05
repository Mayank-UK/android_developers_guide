package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import app.cash.turbine.test
import com.androiddevelopersguide.core.failures.ServerFailure
import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostsUseCase
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
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostsViewModelTest {

    private val getPostsUseCase: GetPostsUseCase = mockk()
    private lateinit var viewModel: PostsViewModel
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
    fun `initial state should emit posts on success`() = runTest {
        val posts = listOf(Post(1, 1, "Title", "Body"))
        coEvery { getPostsUseCase() } returns Result.Success(posts)

        viewModel = PostsViewModel(getPostsUseCase)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(posts, state.posts)
            assertEquals(false, state.isLoading)
            assertNull(state.failure)
        }
    }

    @Test
    fun `error should emit failure`() = runTest {
        val failure = ServerFailure("Error message")
        coEvery { getPostsUseCase() } returns Result.Failed(failure)

        viewModel = PostsViewModel(getPostsUseCase)

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state.posts.isEmpty())
            assertEquals(false, state.isLoading)
            assertEquals(failure, state.failure)
        }
    }
}
