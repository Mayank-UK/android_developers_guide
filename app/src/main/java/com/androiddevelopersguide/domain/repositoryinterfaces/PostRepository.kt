package com.androiddevelopersguide.domain.repositoryinterfaces

import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post

interface PostRepository {
    suspend fun getPosts(): Result<List<Post>>
    suspend fun getPostById(id: Int): Result<Post>
}
