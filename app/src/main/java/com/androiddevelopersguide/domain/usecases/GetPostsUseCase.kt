package com.androiddevelopersguide.domain.usecases

import com.androiddevelopersguide.core.Resource
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {
    operator fun invoke(): Flow<Resource<List<Post>>> = repository.getPosts()
}
