package com.androiddevelopersguide.domain.usecases

import app.cash.turbine.test
import com.androiddevelopersguide.core.Resource
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostsUseCaseTest {

    private val repository: PostRepository = mockk()
    private val getPostsUseCase = GetPostsUseCase(repository)

    @Test
    fun `invoke should return flow from repository`() = runTest {
        val posts = listOf(Post(1, 1, "Title", "Body"))
        val expectedResource = Resource.Success(posts)
        
        every { repository.getPosts() } returns flowOf(expectedResource)

        getPostsUseCase().test {
            assertEquals(expectedResource, awaitItem())
            awaitComplete()
        }
    }
}
