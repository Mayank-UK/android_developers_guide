package com.androiddevelopersguide.domain.repositoryinterfaces

import com.androiddevelopersguide.domain.models.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPostById(id: Int): Post
}