package com.androiddevelopersguide.data.remote

import com.androiddevelopersguide.data.remote.dtos.PostDto
import kotlinx.coroutines.delay

class FakeApiService : ApiService {
    private val fakePosts = listOf(
        PostDto(userId = 1, id = 1, title = "Fake Post 1", body = "Body of fake post 1"),
        PostDto(userId = 1, id = 2, title = "Fake Post 2", body = "Body of fake post 2"),
        PostDto(userId = 2, id = 3, title = "Fake Post 3", body = "Body of fake post 3")
    )

    override suspend fun getPosts(): List<PostDto> {
        delay(1000) // Simulate network delay
        return fakePosts
    }

    override suspend fun getPostById(id: Int): PostDto {
        delay(500)
        return fakePosts.find { it.id == id } ?: throw Exception("Post not found")
    }
}
