package com.androiddevelopersguide.data.mappers

import com.androiddevelopersguide.data.local.entities.PostEntity
import com.androiddevelopersguide.data.remote.dtos.PostDto
import com.androiddevelopersguide.domain.models.Post

object PostMapper {
    fun PostDto.toDomain() = Post(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

    fun PostEntity.toDomain() = Post(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

    fun Post.toEntity() = PostEntity(
        id = id,
        userId = userId,
        title = title,
        body = body
    )

    fun Post.toDto() = PostDto(
        id = id,
        userId = userId,
        title = title,
        body = body
    )
}