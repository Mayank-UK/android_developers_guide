package com.androiddevelopersguide.domain.repositoryinterfaces

import com.androiddevelopersguide.core.Resource
import com.androiddevelopersguide.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<Resource<List<Post>>>
    suspend fun getPostById(id: Int): Resource<Post>
}
