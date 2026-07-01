package com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts

import app.cash.turbine.test
import com.androiddevelopersguide.core.Resource
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.usecases.GetPostsUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
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
        every { getPostsUseCase() } returns flowOf(
            Resource.Loading,
            Resource.Success(posts)
        )

        viewModel = PostsViewModel(getPostsUseCase)

        viewModel.posts.test {
            assertEquals(posts, awaitItem())
        }
        
        viewModel.isLoading.test {
            assertEquals(false, awaitItem())
        }
    }

    @Test
    fun `error should emit error message`() = runTest {
        val errorMessage = "Error message"
        every { getPostsUseCase() } returns flowOf(
            Resource.Loading,
            Resource.Error(errorMessage)
        )

        viewModel = PostsViewModel(getPostsUseCase)

        viewModel.error.test {
            assertEquals(errorMessage, awaitItem())
        }
        
        viewModel.isLoading.test {
            assertEquals(false, awaitItem())
        }
    }
}
