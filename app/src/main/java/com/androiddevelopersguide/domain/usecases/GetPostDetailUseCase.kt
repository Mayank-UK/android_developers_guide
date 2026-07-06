package com.androiddevelopersguide.domain.usecases

import com.androiddevelopersguide.core.result.Result
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import javax.inject.Inject

class GetPostDetailUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(id: Int): Result<Post> = repository.getPostById(id)
}
