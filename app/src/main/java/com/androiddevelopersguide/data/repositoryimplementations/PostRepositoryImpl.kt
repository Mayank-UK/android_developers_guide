package com.androiddevelopersguide.data.repositoryimplementations

import com.androiddevelopersguide.data.local.daos.PostDao
import com.androiddevelopersguide.data.mappers.PostMapper.toDomain
import com.androiddevelopersguide.data.mappers.PostMapper.toEntity
import com.androiddevelopersguide.data.remote.ApiService
import com.androiddevelopersguide.domain.models.Post
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: PostDao
) : PostRepository {

    override suspend fun getPosts(): List<Post> {
        return try {
            val remoteUsers = api.getPosts()
            dao.insertPosts(remoteUsers.map { it.toDomain().toEntity() })
            dao.getPosts().map { it.toDomain() }
        } catch (e: Exception) {
            dao.getPosts().map { it.toDomain() }
        }
    }

    override suspend fun getPostById(id: Int): Post {
        return try {
            api.getPostById(id).toDomain()
        } catch (e: Exception) {
            dao.getPostById(id)?.toDomain()
        }!!
    }
}