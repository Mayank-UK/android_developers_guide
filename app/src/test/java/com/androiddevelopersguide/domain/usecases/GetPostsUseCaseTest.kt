package com.androiddevelopersguide.domain.usecases

import com.androiddevelopersguide.core.failures.ServerFailure
import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetPostsUseCaseTest {

    private val repository: PostRepository = mockk()
    private val getPostsUseCase = GetPostsUseCase(repository)

    @Test
    fun `invoke should return success result from repository`() = runTest {
        val posts = listOf(Post(1, 1, "Title", "Body"))
        val expected = Result.Success(posts)
        coEvery { repository.getPosts() } returns expected

        assertEquals(expected, getPostsUseCase())
    }

    @Test
    fun `invoke should return failed result from repository`() = runTest {
        val expected = Result.Failed<List<Post>>(ServerFailure("Server error"))
        coEvery { repository.getPosts() } returns expected

        assertEquals(expected, getPostsUseCase())
    }
}
