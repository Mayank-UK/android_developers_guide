package com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail

import app.cash.turbine.test
import com.androiddevelopersguide.core.Resource
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
        coEvery { getPostDetailUseCase(1) } returns Resource.Success(post)

        viewModel = PostDetailViewModel(getPostDetailUseCase)
        viewModel.loadPost(1)

        viewModel.post.test {
            assertEquals(post, awaitItem())
        }
        
        viewModel.isLoading.test {
            assertEquals(false, awaitItem())
        }
    }

    @Test
    fun `loadPost should update state on error`() = runTest {
        val errorMessage = "Error loading post"
        coEvery { getPostDetailUseCase(1) } returns Resource.Error(errorMessage)

        viewModel = PostDetailViewModel(getPostDetailUseCase)
        viewModel.loadPost(1)

        viewModel.error.test {
            assertEquals(errorMessage, awaitItem())
        }
        
        viewModel.isLoading.test {
            assertEquals(false, awaitItem())
        }
    }
}
