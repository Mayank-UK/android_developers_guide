package com.androiddevelopersguide.domain.usecases

import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(): List<Post> = repository.getPosts()
}