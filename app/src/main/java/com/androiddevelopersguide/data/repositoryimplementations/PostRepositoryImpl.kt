package com.androiddevelopersguide.data.repositoryimplementations

import com.androiddevelopersguide.core.Resource
import com.androiddevelopersguide.core.SafeCall
import com.androiddevelopersguide.data.local.daos.PostDao
import com.androiddevelopersguide.data.mappers.PostMapper.toDomain
import com.androiddevelopersguide.data.mappers.PostMapper.toEntity
import com.androiddevelopersguide.data.remote.ApiService
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: PostDao
) : PostRepository {

    override fun getPosts(): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading)

        // 1. Emit cached data if available
        val cachedPosts = dao.getPosts().map { it.toDomain() }
        if (cachedPosts.isNotEmpty()) {
            emit(Resource.Success(cachedPosts))
        }

        // 2. Fetch from remote and update cache
        try {
            val remotePosts = api.getPosts()
            dao.insertPosts(remotePosts.map { it.toDomain().toEntity() })
            // Emit updated data from cache (single source of truth)
            emit(Resource.Success(dao.getPosts().map { it.toDomain() }))
        } catch (e: Exception) {
            // If remote fails but we have cache, we already emitted it.
            // If cache was empty, emit error.
            if (cachedPosts.isEmpty()) {
                emit(Resource.Error("Could not fetch posts", e))
            }
        }
    }

    override suspend fun getPostById(id: Int): Resource<Post> {
        return SafeCall.execute {
            // Try remote first
            try {
                api.getPostById(id).toDomain()
            } catch (e: Exception) {
                // Fallback to local
                dao.getPostById(id)?.toDomain() ?: throw e
            }
        }
    }
}
